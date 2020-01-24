package com.andor.watchit.screens.searchmovie.view

import android.app.SearchManager
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import com.andor.watchit.R
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.Event

class SearchMovieViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater
) : SearchMovieViewMvc, BaseObservableViewMvc<Event>() {
    init {
        setRootView(inflater.inflate(R.layout.search_movie_fragment, parent, false))
    }

    override fun setSearchBar(
        menu: Menu,
        activity: FragmentActivity
    ) {
        val searchActionMenuItem = menu.findItem(R.id.search)
        searchActionMenuItem.expandActionView()
        val searchView = searchActionMenuItem.actionView

        if (searchView is SearchView) {

            searchView.apply {
                // Assumes current activity is the searchable activity
                val searchManager = context.getSystemService(Context.SEARCH_SERVICE)

                if (searchManager is SearchManager) {
                    setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
                }

                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        getEventStream().onNext(Event.FindMovie(newText))
                        return true
                    }

                })
            }
        }
    }
}