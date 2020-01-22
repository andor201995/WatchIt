package com.andor.watchit.screens.moviedetail.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.moviedetail.model.Event
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface MovieDetailViewMvc : ObservableViewMvc<Event> {
    fun setMovieDetails(movieDetail: TopRatedMovie)
}