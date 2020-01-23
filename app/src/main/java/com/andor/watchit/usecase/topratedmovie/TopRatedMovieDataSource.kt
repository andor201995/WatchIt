package com.andor.watchit.usecase.topratedmovie

import androidx.paging.PageKeyedDataSource
import com.andor.watchit.usecase.common.datasource.GeneralMovie
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executor

class TopRatedMovieDataSource(
    private val useCase: TopRatedMovieUseCase,
    private val retryExecutor: Executor
) :
    PageKeyedDataSource<Long, GeneralMovie>() {

    val initialNetworkStateStream: PublishSubject<NetworkState.Initial> = PublishSubject.create()
    val nextNetworkStateStream: PublishSubject<NetworkState.Next> = PublishSubject.create()

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, GeneralMovie>
    ) {
        initialNetworkStateStream.onNext(NetworkState.Initial.Loading)
        nextNetworkStateStream.onNext(NetworkState.Next.Loading)

        useCase.fetchTopRatedMovieAndNotify(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TopRatedMovieUseCaseImpl.FetchResult> {
                override fun onSuccess(t: TopRatedMovieUseCaseImpl.FetchResult) {
                    when (t) {
                        is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                            initialNetworkStateStream.onNext(NetworkState.Initial.Success)
                            nextNetworkStateStream.onNext(NetworkState.Next.Success)
                            if (t.pageNumber == 1) {
                                retry = null
                                callback.onResult(t.generalMovieList, null, 2L)
                            }
                        }
                        is TopRatedMovieUseCaseImpl.FetchResult.Failure -> {
                            retry = {
                                loadInitial(params, callback)
                            }
                            initialNetworkStateStream.onNext(NetworkState.Initial.Error)
                            nextNetworkStateStream.onNext(NetworkState.Next.Error)
                        }
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GeneralMovie>) {

        nextNetworkStateStream.onNext(NetworkState.Next.Loading)
        useCase.fetchTopRatedMovieAndNotify(params.key.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TopRatedMovieUseCaseImpl.FetchResult> {
                override fun onSuccess(t: TopRatedMovieUseCaseImpl.FetchResult) {
                    when (t) {
                        is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                            nextNetworkStateStream.onNext(NetworkState.Next.Success)
                            //check for last
                            val nextPage =
                                if (t.maxPageCount.toLong() == params.key) {
                                    nextNetworkStateStream.onNext(NetworkState.Next.Completed)
                                    null
                                } else {
                                    params.key + 1
                                }

                            retry = null
                            callback.onResult(t.generalMovieList, nextPage)
                        }
                        is TopRatedMovieUseCaseImpl.FetchResult.Failure -> {
                            retry = {
                                loadAfter(params, callback)
                            }
                            nextNetworkStateStream.onNext(NetworkState.Next.Error)
                        }
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, GeneralMovie>) {
        //leave it empty for now...
    }

    sealed class NetworkState {
        sealed class Initial : NetworkState() {
            object Loading : Initial()
            object Error : Initial()
            object Success : Initial()
        }

        sealed class Next : NetworkState() {
            object Loading : Next()
            object Error : Next()
            object Success : Next()
            object Completed : Next()
        }
    }
}