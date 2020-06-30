package com.andor.watchit.usecase.movie

import io.reactivex.subjects.SingleSubject

interface TopRatedMovieUseCase {
    fun fetchTopRatedMovieAndNotify(pageNumber: Int):
            SingleSubject<TopRatedMovieUseCaseImpl.FetchResult>
}
