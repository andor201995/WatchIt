package com.andor.watchit.screens.topratedmovielist.topratedlistitem

import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListItemViewMvc : ViewMvc {
    fun updateView(topRatedMovie: TopRatedMovie)
}
