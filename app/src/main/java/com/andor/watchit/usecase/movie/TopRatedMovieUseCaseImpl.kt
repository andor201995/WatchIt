package com.andor.watchit.usecase.movie

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.endpoints.movie.TopRatedMovieListEndPoint
import com.andor.watchit.repository.movie.MovieRepository
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject
import kotlin.math.ceil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TopRatedMovieUseCaseImpl @Inject constructor(
    private val topRatedMovieListEndPoint: TopRatedMovieListEndPoint,
    private val repository: MovieRepository
) :
    TopRatedMovieUseCase {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    sealed class FetchResult {
        data class Success(
            val generalMovieList: List<GeneralMovie>,
            val pageNumber: Int,
            val maxPageCount: Int,
            val totalResults: Int
        ) : FetchResult()

        object Failure : FetchResult()
    }

    override fun fetchTopRatedMovieAndNotify(pageNumber: Int): SingleSubject<FetchResult> {
        val topRatedMovieFetchEvent: SingleSubject<FetchResult> = SingleSubject.create()
        topRatedMovieListEndPoint.onFetchTopRatedMovieListAndNotify(
            pageNumber,
            getFetchListener(topRatedMovieFetchEvent, pageNumber)
        )

        return topRatedMovieFetchEvent
    }

    private fun getFetchListener(
        topRatedMovieFetchEvent: SingleSubject<FetchResult>,
        pageNumber: Int
    ): TopRatedMovieListEndPoint.Listener = object : TopRatedMovieListEndPoint.Listener {
        override fun onFetchSuccess(topRatedMovieSchema: TopRatedMovieSchema) {

            val generalMovies = Converter.convertFrom(
                topRatedMovieSchema
            )

            coroutineScope.launch {
                repository.addAllMovie(generalMovies)
                getMovieByRatingFromDbAndNotify(pageNumber, topRatedMovieFetchEvent)
            }
        }

        override fun onFetchFailed() {
            coroutineScope.launch {
                getMovieByRatingFromDbAndNotify(pageNumber, topRatedMovieFetchEvent)
            }
        }
    }

    private suspend fun getMovieByRatingFromDbAndNotify(
        pageNumber: Int,
        topRatedMovieFetchEvent: SingleSubject<FetchResult>
    ) {
        val cachedMovies = repository.getPagedMovies(pageNumber)
        val cacheMovieCount = repository.getMovieCount()

        if (cacheMovieCount > 0) {
            topRatedMovieFetchEvent.onSuccess(
                FetchResult.Success(
                    cachedMovies,
                    pageNumber,
                    ceil(cacheMovieCount / 20f).toInt(),
                    cacheMovieCount
                )
            )
        } else {
            topRatedMovieFetchEvent.onSuccess(
                FetchResult.Failure
            )
        }
    }
}
