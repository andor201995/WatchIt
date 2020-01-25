package com.andor.watchit.screens.searchmovie.model

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.findmovie.FindMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class SearchMovieViewModel(private val findMovieDataSourceFactory: FindMovieDataSourceFactory) :
    ViewModel() {

    var movieStream: BehaviorSubject<PagedList<GeneralMovie>> = BehaviorSubject.create()
    val nextNetworkStateStream: BehaviorSubject<NetworkState.Next> =
        BehaviorSubject.create()
    val initialNetworkStateStream: BehaviorSubject<NetworkState.Initial> =
        BehaviorSubject.create()


    init {

        findMovieDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.nextNetworkStateStream
            }.subscribe(nextNetworkStateStream)

        findMovieDataSourceFactory
            .getDataSourceStream()
            .flatMap {
                it.initialNetworkStateStream
            }.subscribe(initialNetworkStateStream)

    }

    private fun getPageConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(60)
            .setPageSize(20)
            .setMaxSize(200)
            .build()
    }

    fun retryLoadingList() {

    }

    fun findMovie(query: String) {
        if (query.isNotBlank()) {
            findMovieDataSourceFactory.query = query
            RxPagedListBuilder(findMovieDataSourceFactory, getPageConfig())
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .subscribe(movieStream)
        }
    }
}
