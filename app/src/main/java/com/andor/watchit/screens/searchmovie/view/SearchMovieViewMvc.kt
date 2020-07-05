package com.andor.watchit.screens.searchmovie.view

import android.app.SearchManager
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.core.extensions.gone
import com.andor.watchit.core.extensions.visible
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.ScreenUtils
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.searchmovie.controller.SearchMovieListAdapter
import com.andor.watchit.screens.searchmovie.model.SearchViewEvent
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.todkars.shimmer.ShimmerRecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

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

class SearchMovieViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : SearchMovieViewMvc, BaseObservableViewMvc<SearchViewEvent>() {
    private var emptyPlaceHolderContainer: View
    private var shimmerRecyclerView: ShimmerRecyclerView
    private var loader: View
    private lateinit var adapter: SearchMovieListAdapter
    private var placeHolderContainer: View
    private var recyclerView: RecyclerView

    init {
        setRootView(inflater.inflate(R.layout.search_movie_fragment, parent, false))
        recyclerView = findViewById(R.id.searchList)
        placeHolderContainer = findViewById(R.id.searchFirstPlaceHolder)
        emptyPlaceHolderContainer = findViewById(R.id.searchEmptyPlaceHolder)
        shimmerRecyclerView = findViewById(R.id.shimmer_recycler_view)

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

            val gridCount = ScreenUtils.getPossibleGridCount(context)

            recyclerView.layoutManager = GridLayoutManager(context, gridCount)
            shimmerRecyclerView.layoutManager = GridLayoutManager(context, gridCount)
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
                getEventStream().onNext(SearchViewEvent.SearchCollapse)
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
                        getEventStream().onNext(SearchViewEvent.FindMovie(it.toString()))
                    }
            }
        }
    }

    override fun hidePlaceHolder() {
        placeHolderContainer.gone()
    }

    override fun updateList(list: PagedList<MovieUiModel>) {
        adapter.submitList(list)
    }

    override fun showPlaceHolder() {
        placeHolderContainer.visible()
    }

    override fun hideLoader() {
        shimmerRecyclerView.hideShimmer()
        loader.gone()
    }

    override fun showLoader() {
        shimmerRecyclerView.showShimmer()
        loader.visible()
    }

    override fun showEmptyListPlaceholder() {
        emptyPlaceHolderContainer.visible()
    }

    override fun hideEmptyListPlaceholder() {
        emptyPlaceHolderContainer.gone()
    }
}
