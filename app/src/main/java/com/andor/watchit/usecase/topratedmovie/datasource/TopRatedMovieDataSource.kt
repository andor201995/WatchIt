package com.andor.watchit.usecase.topratedmovie.datasource

import androidx.paging.PageKeyedDataSource
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TopRatedMovieDataSource(private val useCase: TopRatedMovieUseCase) :
    PageKeyedDataSource<Long, TopRatedMovie>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, TopRatedMovie>
    ) {
        useCase.fetchTopRatedMovieAndNotify(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TopRatedMovieUseCaseImpl.FetchResult> {
                override fun onSuccess(t: TopRatedMovieUseCaseImpl.FetchResult) {
                    when (t) {
                        is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                            if (t.pageNumber == 1) {
                                callback.onResult(t.topRatedMovieList, null, 2L)
                            }
                        }
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, TopRatedMovie>) {
        useCase.fetchTopRatedMovieAndNotify(params.key.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TopRatedMovieUseCaseImpl.FetchResult> {
                override fun onSuccess(t: TopRatedMovieUseCaseImpl.FetchResult) {
                    when (t) {
                        is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                            //check for last
                            val nextPage =
                                if (t.maxPageCount.toLong() == params.key) null else params.key + 1
                            callback.onResult(t.topRatedMovieList, nextPage)
                        }
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, TopRatedMovie>) {
        //leave it empty for now...
    }
}