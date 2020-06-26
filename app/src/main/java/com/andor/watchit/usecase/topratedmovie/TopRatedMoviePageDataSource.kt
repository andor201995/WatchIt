package com.andor.watchit.usecase.topratedmovie

import com.andor.watchit.core.rx.RxBaseSingleObserver
import com.andor.watchit.usecase.common.datasource.PageKeyedDataSourceWithRetry
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.common.model.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

class TopRatedMoviePageDataSource(
    private val useCase: TopRatedMovieUseCase,
    retryExecutor: Executor
) :
    PageKeyedDataSourceWithRetry<Long, GeneralMovie, NetworkState.Initial, NetworkState.Next>(
        retryExecutor
    ) {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, GeneralMovie>
    ) {
        initialNetworkStateStream.onNext(NetworkState.Initial.Loading)
        nextNetworkStateStream.onNext(NetworkState.Next.Loading)

        useCase.fetchTopRatedMovieAndNotify(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RxBaseSingleObserver<TopRatedMovieUseCaseImpl.FetchResult>() {
                override fun onSuccess(t: TopRatedMovieUseCaseImpl.FetchResult) {
                    when (t) {
                        is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                            initialNetworkStateStream.onNext(
                                NetworkState.Initial.Success(t.totalResults)
                            )
                            nextNetworkStateStream.onNext(NetworkState.Next.Success)
                            if (t.pageNumber == 1) {
                                retry = null
                                callback.onResult(
                                    t.generalMovieList, null, 2L
                                )
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
            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, GeneralMovie>) {

        nextNetworkStateStream.onNext(NetworkState.Next.Loading)
        useCase.fetchTopRatedMovieAndNotify(params.key.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RxBaseSingleObserver<TopRatedMovieUseCaseImpl.FetchResult>() {
                override fun onSuccess(t: TopRatedMovieUseCaseImpl.FetchResult) {
                    when (t) {
                        is TopRatedMovieUseCaseImpl.FetchResult.Success -> {
                            nextNetworkStateStream.onNext(NetworkState.Next.Success)
                            // check for last
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
            })
    }
}
