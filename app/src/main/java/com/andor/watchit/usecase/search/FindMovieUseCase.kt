package com.andor.watchit.usecase.search

import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.Single

interface FindMovieUseCase {
    sealed class FetchResult {
        data class Success(
            val generalMovieList: List<GeneralMovie>,
            val pageNumber: Int,
            val maxPageCount: Int,
            val totalResult: Int
        ) : FetchResult()

        object Failure : FetchResult()
    }

    fun findMovie(page: Int, query: String): Single<FetchResult>
}
