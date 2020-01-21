package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListItemViewMvc : ObservableViewMvc<Event> {
    fun updateView(topRatedMovie: TopRatedMovie)
}
