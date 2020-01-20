package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc

interface TopRatedMovieListItemLoaderViewMvc :
    ObservableViewMvc<TopRatedMovieListItemLoaderViewMvc.Event> {

    sealed class Event {
        object RetryListLoading : Event()
    }

    fun showLoader()
    fun showError()
    fun hideRow()
}