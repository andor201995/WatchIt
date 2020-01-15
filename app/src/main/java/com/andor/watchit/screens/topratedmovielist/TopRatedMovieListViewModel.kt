package com.andor.watchit.screens.topratedmovielist

import androidx.lifecycle.ViewModel
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class TopRatedMovieListViewModel(private val topRatedMovieUseCase: TopRatedMovieUseCase) :
    ViewModel() {
    val screenStateStream: BehaviorSubject<TopRatedMovieScreenState> =
        BehaviorSubject.create()

    private val useCaseObserver = object : Observer<TopRatedMovieUseCase.FetchResult> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: TopRatedMovieUseCase.FetchResult) {
            when (t) {
                is TopRatedMovieUseCase.FetchResult.Success -> {
                    screenStateStream.onNext(
                        TopRatedMovieScreenState(
                            t.topRatedMovieList,
                            ScreenStatus.FETCH_SUCCESS
                        )
                    )
                }
                is TopRatedMovieUseCase.FetchResult.Failure -> {
                    screenStateStream.onNext(
                        TopRatedMovieScreenState(
                            listOf(),
                            ScreenStatus.FETCH_SUCCESS
                        )
                    )
                }
            }
        }

        override fun onError(e: Throwable) {
        }

    }

    init {
        bindStream()
    }

    private fun bindStream() {
        topRatedMovieUseCase.getResultStream().subscribe(useCaseObserver)
    }


    fun fetchTopRatedMovieAndNotify() {
        topRatedMovieUseCase.fetchTopRatedMovieAndNotify()
    }
}
