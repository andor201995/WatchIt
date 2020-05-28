package com.andor.watchit.usecase.topratedmovie

import io.reactivex.subjects.SingleSubject

interface TopRatedMovieUseCase {
    fun fetchTopRatedMovieAndNotify(pageNumber: Int): SingleSubject<TopRatedMovieUseCaseImpl.FetchResult>
}
