package com.andor.watchit.screens.searchmovie.view

import android.view.Menu
import androidx.fragment.app.FragmentActivity
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.Event

interface SearchMovieViewMvc : ObservableViewMvc<Event> {
    fun setSearchBar(
        menu: Menu,
        activity: FragmentActivity
    )
}