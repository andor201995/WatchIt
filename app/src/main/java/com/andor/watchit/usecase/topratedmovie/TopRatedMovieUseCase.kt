package com.andor.watchit.usecase.topratedmovie

import io.reactivex.subjects.PublishSubject

interface TopRatedMovieUseCase {
    fun fetchTopRatedMovieAndNotify()
    fun getResultStream(): PublishSubject<TopRatedMovieUseCaseImpl.FetchResult>
}