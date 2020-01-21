package com.andor.watchit.screens.moviedetail.view

import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface MovieDetailViewMvc : ViewMvc {
    fun setMovieDetails(movieDetail: TopRatedMovie)
}