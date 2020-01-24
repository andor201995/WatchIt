package com.andor.watchit.usecase.findmovie

import io.reactivex.Single

interface FindMovieUseCase {
    fun findMovie(page: Int, query: String): Single<FindMovieUseCaseImpl.FetchResult>
}