package com.andor.watchit.screens.topratedmovielist.view

import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.MovieListEvent
import com.andor.watchit.usecase.common.model.GeneralMovie

interface TopRatedMovieListViewMvc :
    ObservableViewMvc<MovieListEvent> {
    fun updateList(listOfGeneralMovie: PagedList<GeneralMovie>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
    fun selectOptionItem(itemId: Int)
}
