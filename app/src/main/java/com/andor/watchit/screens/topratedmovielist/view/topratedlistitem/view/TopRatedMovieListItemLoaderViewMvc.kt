package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event

interface TopRatedMovieListItemLoaderViewMvc :
    ObservableViewMvc<Event> {
    fun showLoader()
    fun showError()
    fun hideRow()
}