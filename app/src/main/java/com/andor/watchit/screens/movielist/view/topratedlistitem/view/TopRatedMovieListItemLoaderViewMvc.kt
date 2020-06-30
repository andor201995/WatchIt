package com.andor.watchit.screens.movielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.movielist.model.MovieListEvent

interface TopRatedMovieListItemLoaderViewMvc :
    ObservableViewMvc<MovieListEvent> {
    fun showLoader()
    fun showError()
    fun hideRow()
}
