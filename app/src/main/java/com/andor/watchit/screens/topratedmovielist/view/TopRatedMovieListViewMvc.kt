package com.andor.watchit.screens.topratedmovielist.view

import android.view.Menu
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

interface TopRatedMovieListViewMvc :
    ObservableViewMvc<Event> {
    fun updateList(listOfTopRatedMovie: PagedList<TopRatedMovie>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
    fun setSearchBar(
        menu: Menu,
        requireActivity: FragmentActivity
    )
}