package com.andor.watchit.screens.tvlist.controller

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.common.model.TvUiModel
import com.andor.watchit.usecase.tv.TvListPageDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TvListViewModel @Inject constructor(
    private val tvListPageDataSourceFactory: TvListPageDataSourceFactory
) : ViewModel() {
    var tvStream: BehaviorSubject<PagedList<TvUiModel>> = BehaviorSubject.create()
    val nextNetworkStateStream: BehaviorSubject<NetworkState.Next> =
        BehaviorSubject.create()
    val initialNetworkStateStream: BehaviorSubject<NetworkState.Initial> =
        BehaviorSubject.create()

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(60)
            .setPageSize(20)
            .setMaxSize(200)
            .build()

        RxPagedListBuilder(tvListPageDataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .subscribe(tvStream)

        tvListPageDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.nextNetworkStateStream
            }.subscribe(nextNetworkStateStream)

        tvListPageDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.initialNetworkStateStream
            }.subscribe(initialNetworkStateStream)
    }

    fun retryLoadingList() {
        tvListPageDataSourceFactory.pageDataSource.retryAllFailed()
    }
}
