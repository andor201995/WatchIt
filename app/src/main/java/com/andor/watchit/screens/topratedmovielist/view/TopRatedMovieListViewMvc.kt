package com.andor.watchit.screens.topratedmovielist.view

import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListViewMvc:
    ViewMvc {
    fun updateList(listOfTopRatedMovie: List<TopRatedMovie>)
    fun showLoader()
    fun hideLoader()
}