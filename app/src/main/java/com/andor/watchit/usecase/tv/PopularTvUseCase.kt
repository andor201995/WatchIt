package com.andor.watchit.usecase.tv

import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.network.common.schema.TvSchema
import com.andor.watchit.network.tv.PopularTvEndPoint
import com.andor.watchit.repository.tv.TvRepository
import com.andor.watchit.usecase.common.model.TvUiModel
import com.andor.watchit.usecase.tv.PopularTvUseCase.Companion.PAGE_SIZE
import io.reactivex.subjects.SingleSubject
import kotlin.math.ceil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface PopularTvUseCase {
    companion object {
        const val PAGE_SIZE = 20f
    }

    fun getPopularTv(pageNumber: Int): SingleSubject<FetchResult>

    sealed class FetchResult {
        data class Success(
            val TvUiModelList: List<TvUiModel>,
            val pageNumber: Int,
            val maxPageCount: Int,
            val totalResults: Int
        ) : FetchResult()

        object Failure : FetchResult()
    }
}

class PopularTvUseCaseImpl(
    private val popularTvEndPoint: PopularTvEndPoint,
    private val repository: TvRepository
) : PopularTvUseCase {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getPopularTv(pageNumber: Int): SingleSubject<PopularTvUseCase.FetchResult> {
        val result = SingleSubject.create<PopularTvUseCase.FetchResult>()
        val subscribe = popularTvEndPoint.getPopularMovies(pageNumber).onErrorReturn { null }
            .subscribe { schema, _ ->
                handleResult(schema, result, pageNumber)
            }
        return result
    }

    private fun handleResult(
        schema: TvSchema?,
        result: SingleSubject<PopularTvUseCase.FetchResult>,
        pageNumber: Int
    ) {
        if (schema != null) {
            val modelList = Converter.convertFrom(schema)
            coroutineScope.launch {
                repository.addAllTv(modelList)
            }
            result.onSuccess(
                PopularTvUseCase.FetchResult.Success(
                    modelList,
                    schema.page,
                    schema.total_pages,
                    schema.total_results
                )
            )
        } else {
            coroutineScope.launch {
                val cachedMovies = repository.getPagedTv(pageNumber)
                val cacheMovieCount = repository.getTvCount()
                if (cacheMovieCount > 0) {
                    result.onSuccess(
                        PopularTvUseCase.FetchResult.Success(
                            cachedMovies,
                            pageNumber,
                            ceil(cacheMovieCount / PAGE_SIZE).toInt(),
                            cacheMovieCount
                        )
                    )
                } else {
                    result.onSuccess(
                        PopularTvUseCase.FetchResult.Failure
                    )
                }
            }
        }
    }
}
