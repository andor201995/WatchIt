package com.andor.watchit.screens.topratedmovielist.view

import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListViewMvc :
    ViewMvc {
    fun updateList(listOfTopRatedMovie: PagedList<TopRatedMovie>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
}