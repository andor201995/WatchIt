package com.andor.watchit.screens.topratedmovielist.model

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.core.RxBaseObserver
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class TopRatedMovieListViewModel(topRatedMovieDataSourceFactory: TopRatedMovieDataSourceFactory) :
    ViewModel() {

    val screenStateStream: BehaviorSubject<TopRatedMovieScreenState> = BehaviorSubject.create()

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        RxPagedListBuilder(topRatedMovieDataSourceFactory, pagedListConfig)
            .setFetchScheduler(Schedulers.io())
            .buildObservable().subscribe(object : RxBaseObserver<PagedList<TopRatedMovie>>() {
                override fun onNext(t: PagedList<TopRatedMovie>) {
                    screenStateStream.onNext(
                        TopRatedMovieScreenState(
                            ScreenStatus.SUCCESS(t)
                        )
                    )
                }
            })
    }
}
