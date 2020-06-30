package com.andor.watchit.screens.movielist.model

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.movie.TopRatedMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TopRatedMovieListViewModel @Inject constructor(
    private val topRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory
) : ViewModel() {

    var movieUiModelStream: BehaviorSubject<PagedList<MovieUiModel>> = BehaviorSubject.create()
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
