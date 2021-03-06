package com.andor.watchit.screens.searchmovie.controller

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.andor.watchit.network.common.helper.Converter
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.pagesource.FindMovieDataSourceFactory
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(
    private val findMovieDataSourceFactory: FindMovieDataSourceFactory
) : ViewModel() {

    private var lastSearchQuery: String = ""
    var movieUiModelStream: BehaviorSubject<PagedList<MovieUiModel>> = BehaviorSubject.create()
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
        findMovieDataSourceFactory.getDataSourceStream().value?.retryAllFailed()
    }

    fun findMovie(newQuery: String) {
        val query = Converter.convertFrom(newQuery)
        if (query.isNotBlank() && lastSearchQuery != query) {
            lastSearchQuery = query
            findMovieDataSourceFactory.query = query
            RxPagedListBuilder(findMovieDataSourceFactory, getPageConfig())
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .subscribe(movieUiModelStream)
        }
    }
}
