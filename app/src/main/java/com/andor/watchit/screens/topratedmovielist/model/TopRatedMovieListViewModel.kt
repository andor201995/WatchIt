package com.andor.watchit.screens.topratedmovielist.model

import androidx.lifecycle.ViewModel
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class TopRatedMovieListViewModel(private val topRatedMovieUseCase: TopRatedMovieUseCase) :
    ViewModel() {
    val screenStateStream: BehaviorSubject<TopRatedMovieScreenState> =
        BehaviorSubject.create()

    private val useCaseObserver = object : Observer<TopRatedMovieUseCaseImpl.FetchResult> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(t: TopRatedMovieUseCaseImpl.FetchResult) {
            when (t) {
                is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                    screenStateStream.onNext(
                        TopRatedMovieScreenState(
                            t.topRatedMovieList,
                            ScreenStatus.FETCH_SUCCESS
                        )
                    )
                }
                is TopRatedMovieUseCaseImpl.FetchResult.Failure -> {
                    screenStateStream.onNext(
                        TopRatedMovieScreenState(
                            listOf(),
                            ScreenStatus.FETCH_FAILED
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

        screenStateStream.onNext(
            TopRatedMovieScreenState(
                listOf(),
                ScreenStatus.LOADING
            )
        )

        topRatedMovieUseCase.fetchTopRatedMovieAndNotify()
    }
}
