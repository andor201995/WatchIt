package com.andor.watchit.usecase.search

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TopRatedMovieSchema
import com.andor.watchit.network.endpoints.search.FindMovieEndPoint
import com.andor.watchit.repository.movie.MovieRepository
import com.andor.watchit.usecase.common.model.MovieUiModel
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject
import kotlin.math.ceil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface FindMovieUseCase {
    sealed class FetchResult {
        data class Success(
            val movieUiModelList: List<MovieUiModel>,
            val pageNumber: Int,
            val maxPageCount: Int,
            val totalResult: Int
        ) : FetchResult()

        object Failure : FetchResult()
    }

    fun findMovie(page: Int, query: String): Single<FetchResult>
}

class FindMovieUseCaseImpl @Inject constructor(
    private val findMovieEndPoint: FindMovieEndPoint,
    private val repository: MovieRepository
) : FindMovieUseCase {

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun findMovie(page: Int, query: String): SingleSubject<FindMovieUseCase.FetchResult> {
        val responseSingle = SingleSubject.create<FindMovieUseCase.FetchResult>()
        val convertedQuery = Converter.convertFrom(query)
        findMovieEndPoint.findMovieAndNotify(
            page,
            convertedQuery,
            object : FindMovieEndPoint.Listener {
                override fun onFetchFailed() {
                    coroutineScope.launch {
                        val cachedMovieUiModels: List<MovieUiModel> =
                            repository.getMoviesByName(query, page)
                        val cacheMovieCount: Int = repository.getSearchCount(query, page)
                        if (cacheMovieCount > 0) {
                            responseSingle.onSuccess(
                                FindMovieUseCase.FetchResult.Success(
                                    cachedMovieUiModels,
                                    page,
                                    ceil(cacheMovieCount / 20f).toInt(),
                                    cacheMovieCount
                                )
                            )
                        } else {
                            responseSingle.onSuccess(FindMovieUseCase.FetchResult.Failure)
                        }
                    }
                }

                override fun onFetchSuccess(movieSchema: TopRatedMovieSchema) {
                    val movies = Converter.convertFrom(movieSchema)
                    coroutineScope.launch {
                        repository.addAllMovie(movies)
                    }
                    responseSingle.onSuccess(
                        FindMovieUseCase.FetchResult.Success(
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
