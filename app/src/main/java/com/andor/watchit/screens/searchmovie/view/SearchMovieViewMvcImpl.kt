package com.andor.watchit.screens.searchmovie.view

import android.app.SearchManager
import android.content.Context
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.core.inVisible
import com.andor.watchit.core.visible
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.Utils
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.Event
import com.andor.watchit.screens.searchmovie.view.searchmovieitem.controller.SearchMovieListAdapter
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class SearchMovieViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : SearchMovieViewMvc, BaseObservableViewMvc<Event>() {
    private var loader: ContentLoadingProgressBar
    private lateinit var adapter: SearchMovieListAdapter
    private var placeHolderContainer: View
    private var recyclerView: RecyclerView

    init {
        setRootView(inflater.inflate(R.layout.search_movie_fragment, parent, false))
        recyclerView = findViewById(R.id.searchList)
        placeHolderContainer = findViewById(R.id.searchPlaceHolder)
        loader = findViewById(R.id.loader)
        setUpRecyclerView(viewMvcFactory)
    }

    private fun setUpRecyclerView(viewMvcFactory: ViewMvcFactory) {
        if (recyclerView.adapter == null) {

            adapter =
                SearchMovieListAdapter(
                    viewMvcFactory,
                    getEventStream()
                )

            recyclerView.adapter = this.adapter

            val gridCount = Utils.getPossibleGridCount(context)

            recyclerView.layoutManager = GridLayoutManager(context, gridCount)

        } else {
            adapter = recyclerView.adapter as SearchMovieListAdapter
        }
    }

    override fun setSearchBar(
        menu: Menu,
        activity: FragmentActivity,
        mSearchQuery: String
    ) {
        val searchActionMenuItem = menu.findItem(R.id.search)
        searchActionMenuItem.expandActionView()
        searchActionMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                getEventStream().onNext(Event.SearchCollapse)
                return true
            }

        })
        val searchView = searchActionMenuItem.actionView

        if (searchView is SearchView) {
            searchView.apply {
                setQuery(mSearchQuery, false)
                maxWidth = Integer.MAX_VALUE

                // Assumes current activity is the searchable activity
                val searchManager = context.getSystemService(Context.SEARCH_SERVICE)

                if (searchManager is SearchManager) {
                    setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
                }

                queryTextChanges()
                    .debounce(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        getEventStream().onNext(Event.FindMovie(it.toString()))
                    }
            }
        }
    }

    override fun hidePlaceHolder() {
        placeHolderContainer.inVisible()
    }

    override fun updateList(list: PagedList<GeneralMovie>) {
        adapter.submitList(list)
    }

    override fun showPlaceHolder() {
        placeHolderContainer.visible()
    }

    override fun hideLoader() {
        loader.hide()
    }

    override fun showLoader() {
        loader.show()
    }
}