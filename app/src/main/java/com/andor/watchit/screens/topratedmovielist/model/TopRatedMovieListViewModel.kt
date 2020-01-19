package com.andor.watchit.screens.topratedmovielist.model

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.core.RxBaseObserver
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSource
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class TopRatedMovieListViewModel(topRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory) :
    ViewModel() {

    val screenStateStream: BehaviorSubject<TopRatedMovieScreenState> = BehaviorSubject.create()

    private val networkObserver = object :
        RxBaseObserver<TopRatedMovieDataSource.NetworkState>() {
        override fun onNext(t: TopRatedMovieDataSource.NetworkState) {
            when (t) {
                is TopRatedMovieDataSource.NetworkState.ErrorFirst -> {
                    screenStateStream.onNext(TopRatedMovieScreenState(ScreenStatus.FAILED))
                }
                is TopRatedMovieDataSource.NetworkState.LoadingFirst -> {
                    screenStateStream.onNext(TopRatedMovieScreenState(ScreenStatus.LOADING))
                }
                is TopRatedMovieDataSource.NetworkState.LoadingNext -> {
                }
                is TopRatedMovieDataSource.NetworkState.ErrorNext -> {
                }
            }
        }
    }

    private val pagedListObserver = object : RxBaseObserver<PagedList<TopRatedMovie>>() {
        override fun onNext(t: PagedList<TopRatedMovie>) {
            screenStateStream.onNext(
                TopRatedMovieScreenState(
                    ScreenStatus.SUCCESS(t)
                )
            )
        }
    }

    init {

        topRatedMovieDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.networkStateStream
            }.subscribe(networkObserver)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        RxPagedListBuilder(topRatedMovieDataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable().subscribe(pagedListObserver)

    }
}
