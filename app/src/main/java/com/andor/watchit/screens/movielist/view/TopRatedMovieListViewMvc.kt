package com.andor.watchit.screens.movielist.view

import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.movielist.model.MovieListEvent
import com.andor.watchit.usecase.common.model.MovieUiModel

interface TopRatedMovieListViewMvc :
    ObservableViewMvc<MovieListEvent> {
    fun updateList(listOfMovieUiModel: PagedList<MovieUiModel>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
    fun selectOptionItem(itemId: Int)
}
