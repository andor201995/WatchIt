package com.andor.watchit.screens.topratedmovielist.view

import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemLoaderViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListViewMvc :
    ObservableViewMvc<TopRatedMovieListItemLoaderViewMvc.Event> {
    fun updateList(listOfTopRatedMovie: PagedList<TopRatedMovie>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
}