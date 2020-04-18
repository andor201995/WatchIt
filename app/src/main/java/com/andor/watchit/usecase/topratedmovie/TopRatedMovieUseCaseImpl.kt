package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.repository.MovieRepository
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.SingleSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.ceil

class TopRatedMovieUseCaseImpl(
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
        topRatedMovieListEndPoint.onFetchTopRatedMovieListAndNotify(pageNumber,
            object : TopRatedMovieListEndPoint.Listener {
                override fun onFetchSuccess(topRatedMovieSchema: TopRatedMovieSchema) {

                    val generalMovies = Converter.convertFrom(
                        topRatedMovieSchema
                    )

                    coroutineScope.launch {
                        repository.addAllMovie(generalMovies)
                    }

                    topRatedMovieFetchEvent.onSuccess(
                        FetchResult.Success(
                            generalMovies,
                            topRatedMovieSchema.page,
                            topRatedMovieSchema.total_pages,
                            topRatedMovieSchema.total_results
                        )
                    )
                }

                override fun onFetchFailed() {

                    coroutineScope.launch {
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
            })

        return topRatedMovieFetchEvent
    }
}