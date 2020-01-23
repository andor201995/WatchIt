package com.andor.watchit.screens.topratedmovielist.view

import android.view.Menu
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.usecase.common.model.GeneralMovie

interface TopRatedMovieListViewMvc :
    ObservableViewMvc<Event> {
    fun updateList(listOfGeneralMovie: PagedList<GeneralMovie>)
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