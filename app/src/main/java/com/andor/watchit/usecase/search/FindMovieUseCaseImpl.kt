package com.andor.watchit.usecase.search

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.search.FindMovieEndPoint
import com.andor.watchit.repository.movie.MovieRepository
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.search.FindMovieUseCase.FetchResult
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject
import kotlin.math.ceil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FindMovieUseCaseImpl @Inject constructor(
    private val findMovieEndPoint: FindMovieEndPoint,
    private val repository: MovieRepository
) : FindMovieUseCase {

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun findMovie(page: Int, query: String): SingleSubject<FetchResult> {
        val responseSingle = SingleSubject.create<FetchResult>()
        val convertedQuery = Converter.convertFrom(query)
        findMovieEndPoint.findMovieAndNotify(
            page,
            convertedQuery,
            object : FindMovieEndPoint.Listener {
                override fun onFetchFailed() {
                    coroutineScope.launch {
                        val cachedMovies: List<GeneralMovie> =
                            repository.getMoviesByName(query, page)
                        val cacheMovieCount: Int = repository.getSearchCount(query, page)
                        if (cacheMovieCount > 0) {
                            responseSingle.onSuccess(
                                FetchResult.Success(
                                    cachedMovies,
                                    page,
                                    ceil(cacheMovieCount / 20f).toInt(),
                                    cacheMovieCount
                                )
                            )
                        } else {
                            responseSingle.onSuccess(FetchResult.Failure)
                        }
                    }
                }

                override fun onFetchSuccess(movieSchema: TopRatedMovieSchema) {
                    val movies = Converter.convertFrom(movieSchema)
                    coroutineScope.launch {
                        repository.addAllMovie(movies)
                    }
                    responseSingle.onSuccess(
                        FetchResult.Success(
                            movies,
                            movieSchema.page,
                            movieSchema.total_pages,
                            movieSchema.total_results
                        )
                    )
                }
            })
        return responseSingle
    }
}
