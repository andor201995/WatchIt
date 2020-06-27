package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.MovieListEvent

interface TopRatedMovieListItemLoaderViewMvc :
    ObservableViewMvc<MovieListEvent> {
    fun showLoader()
    fun showError()
    fun hideRow()
}
