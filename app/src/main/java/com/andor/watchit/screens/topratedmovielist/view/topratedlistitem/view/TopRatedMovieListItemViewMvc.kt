package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListItemViewMvc : ViewMvc {
    fun updateView(topRatedMovie: TopRatedMovie)
}
