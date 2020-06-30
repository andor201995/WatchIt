package com.andor.watchit.screens.searchmovie.view

import android.view.Menu
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.SearchViewEvent
import com.andor.watchit.usecase.common.model.MovieUiModel

interface SearchMovieViewMvc : ObservableViewMvc<SearchViewEvent> {
    fun setSearchBar(
        menu: Menu,
        activity: FragmentActivity,
        mSearchQuery: String
    )

    fun hidePlaceHolder()
    fun updateList(list: PagedList<MovieUiModel>)
    fun showPlaceHolder()
    fun hideLoader()
    fun showLoader()
    fun showEmptyListPlaceholder()
    fun hideEmptyListPlaceholder()
}
