package com.andor.watchit.usecase.tv

import androidx.paging.DataSource
import com.andor.watchit.core.rx.RxBaseSingleObserver
import com.andor.watchit.usecase.common.datasource.PageKeyedDataSourceWithRetry
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.common.model.TvUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executor
import javax.inject.Inject

class TvListPageDataSource @Inject constructor(
    private val useCase: PopularTvUseCase,
    retryExecutor: Executor
) : PageKeyedDataSourceWithRetry<Long, TvUiModel, NetworkState.Initial, NetworkState.Next>(
    retryExecutor
) {
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, TvUiModel>
    ) {
        initialNetworkStateStream.onNext(NetworkState.Initial.Loading)
        nextNetworkStateStream.onNext(NetworkState.Next.Loading)

        useCase.getPopularTv(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RxBaseSingleObserver<PopularTvUseCase.FetchResult>() {
                override fun onSuccess(t: PopularTvUseCase.FetchResult) {
                    when (t) {
                        is PopularTvUseCase.FetchResult.Success -> {
                            initialNetworkStateStream.onNext(
                                NetworkState.Initial.Success(t.totalResults)
                            )
                            nextNetworkStateStream.onNext(NetworkState.Next.Success)
                            if (t.pageNumber == 1) {
                                retry = null
                                callback.onResult(
                                    t.TvUiModelList, null, 2L
                                )
                            }
                        }
                        is PopularTvUseCase.FetchResult.Failure -> {
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

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, TvUiModel>) {
        nextNetworkStateStream.onNext(NetworkState.Next.Loading)
        useCase.getPopularTv(params.key.toInt())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RxBaseSingleObserver<PopularTvUseCase.FetchResult>() {
                override fun onSuccess(t: PopularTvUseCase.FetchResult) {
                    when (t) {
                        is PopularTvUseCase.FetchResult.Success -> {
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
                            callback.onResult(t.TvUiModelList, nextPage)
                        }
                        is PopularTvUseCase.FetchResult.Failure -> {
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

class TvListPageDataSourceFactory @Inject constructor(
    internal val pageDataSource: TvListPageDataSource
) : DataSource.Factory<Long, TvUiModel>() {

    private val mDataSourceRelay: PublishSubject<TvListPageDataSource> = PublishSubject.create()

    override fun create(): DataSource<Long, TvUiModel> {
        mDataSourceRelay.onNext(pageDataSource)
        return pageDataSource
    }

    fun getDataSourceStream(): PublishSubject<TvListPageDataSource> {
        return mDataSourceRelay
    }
}
