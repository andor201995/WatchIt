package com.andor.watchit.screens.topratedmovielist.model

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSource
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class TopRatedMovieListViewModel(private val topRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory) :
    ViewModel() {

    var topRatedMovieStream: BehaviorSubject<PagedList<TopRatedMovie>> = BehaviorSubject.create()
    val nextNetworkStateStream: BehaviorSubject<TopRatedMovieDataSource.NetworkState.Next> =
        BehaviorSubject.create()
    val initialNetworkStateStream: BehaviorSubject<TopRatedMovieDataSource.NetworkState.Initial> =
        BehaviorSubject.create()


    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(60)
            .setPageSize(20)
            .setMaxSize(200)
            .build()

        RxPagedListBuilder(topRatedMovieDataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .subscribe(topRatedMovieStream)

        topRatedMovieDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.nextNetworkStateStream
            }.subscribe(nextNetworkStateStream)

        topRatedMovieDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.initialNetworkStateStream
            }.subscribe(initialNetworkStateStream)

    }

    fun retryLoadingList() {
        topRatedMovieDataSourceFactory.topRatedMovieDataSource.retryAllFailed()
    }
}
