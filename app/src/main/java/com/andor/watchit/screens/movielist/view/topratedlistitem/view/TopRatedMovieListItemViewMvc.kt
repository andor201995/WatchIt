package com.andor.watchit.screens.movielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.movielist.model.MovieListEvent
import com.andor.watchit.usecase.common.model.GeneralMovie

interface TopRatedMovieListItemViewMvc : ObservableViewMvc<MovieListEvent> {
    fun updateView(generalMovie: GeneralMovie)
    fun cleanUp()
}
