package com.andor.watchit.screens.movielist.controller

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.screens.common.helper.ScreenUtils
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.pagesource.TopRatedMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val topRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory
) : ViewModel() {

    var movieUiModelStream: BehaviorSubject<PagedList<MovieUiModel>> = BehaviorSubject.create()

    val nextNetworkStateStream: BehaviorSubject<NetworkState.Next> =
        BehaviorSubject.create()
    val initialNetworkStateStream: BehaviorSubject<NetworkState.Initial> =
        BehaviorSubject.create()

    init {
        val pagedListConfig = ScreenUtils.getPageListConfig()

        RxPagedListBuilder(topRatedMovieDataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable()
            .subscribe(movieUiModelStream)

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
        topRatedMovieDataSourceFactory.topRatedMoviePageDataSource.retryAllFailed()
    }
}
