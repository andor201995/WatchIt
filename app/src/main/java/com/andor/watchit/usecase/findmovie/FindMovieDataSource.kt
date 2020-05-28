package com.andor.watchit.usecase.findmovie

import com.andor.watchit.core.rx.RxBaseSingleObserver
import com.andor.watchit.usecase.common.datasource.PageKeyedDataSourceWithRetry
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.findmovie.FindMovieUseCase.FetchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

class FindMovieDataSource(
    private val useCase: FindMovieUseCase,
    private val query: String,
    retryExecutor: Executor
) : PageKeyedDataSourceWithRetry<Long, GeneralMovie, NetworkState.Initial, NetworkState.Next>(
    retryExecutor
) {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, GeneralMovie>
    ) {
        initialNetworkStateStream.onNext(NetworkState.Initial.Loading)
        nextNetworkStateStream.onNext(NetworkState.Next.Loading)

        useCase.findMovie(1, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RxBaseSingleObserver<FetchResult>() {
                override fun onSuccess(t: FetchResult) {
                    when (t) {
                        is FetchResult.Success -> {
                            initialNetworkStateStream.onNext(NetworkState.Initial.Success(t.totalResult))
                            nextNetworkStateStream.onNext(NetworkState.Next.Success)
                            if (t.pageNumber == 1) {
                                retry = null
                                callback.onResult(t.generalMovieList, null, 2L)
                            }
                        }
                        is FetchResult.Failure -> {
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
        useCase.findMovie(params.key.toInt(), query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RxBaseSingleObserver<FetchResult>() {
                override fun onSuccess(t: FetchResult) {
                    when (t) {
                        is FetchResult.Success -> {
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
                        is FetchResult.Failure -> {
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
