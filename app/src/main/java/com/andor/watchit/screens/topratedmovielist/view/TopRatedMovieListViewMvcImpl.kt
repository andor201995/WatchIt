package com.andor.watchit.screens.topratedmovielist.view

import android.app.SearchManager
import android.content.Context
import android.util.DisplayMetrics
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.core.visible
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller.TopRatedMovieListAdapter
import com.andor.watchit.usecase.common.datasource.GeneralMovie


class TopRatedMovieListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<Event>(),
    TopRatedMovieListViewMvc {

    private var loader: ContentLoadingProgressBar
    private var recyclerView: RecyclerView
    private lateinit var adapter: TopRatedMovieListAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.top_rated_movie_list_fragment, parent, false))
        recyclerView = findViewById(R.id.top_rated_movie_list)
        loader = findViewById(R.id.loader)
        setUpRecyclerView(viewMvcFactory)
    }

    private fun setUpRecyclerView(viewMvcFactory: ViewMvcFactory) {
        if (recyclerView.adapter == null) {
            adapter =
                TopRatedMovieListAdapter(
                    viewMvcFactory,
                    getEventStream()
                )

            recyclerView.adapter = this.adapter

            val gridCount = getPossibleGridCount()

            recyclerView.layoutManager = GridLayoutManager(context, gridCount)
        } else {
            adapter = recyclerView.adapter as TopRatedMovieListAdapter
        }


    }

    private fun getPossibleGridCount(): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density: Float = context.resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density

        val dimension = context.resources.getDimension(R.dimen.itemWidth)
        val possibleGridCount = (dpWidth / dimension).toInt()
        return if (possibleGridCount > 2) possibleGridCount else 2

    }

    override fun updateList(listOfGeneralMovie: PagedList<GeneralMovie>) {
        adapter.submitList(listOfGeneralMovie)
    }

    override fun showLoader() {
        loader.show()
        loader.visible()
    }

    override fun hideLoader() {
        loader.hide()
    }

    override fun showListLoadingError() {
        adapter.setListLoadingState(TopRatedMovieListAdapter.ListLoading.Error)
    }

    override fun showListLoadingCompleted() {
        adapter.setListLoadingState(TopRatedMovieListAdapter.ListLoading.Completed)
    }

    override fun showListLoading() {
        adapter.setListLoadingState(TopRatedMovieListAdapter.ListLoading.Loading)
    }

    override fun setSearchBar(
        menu: Menu,
        activity: FragmentActivity
    ) {
        //SearchView setup
        val searchActionMenuItem = menu.findItem(R.id.search)
        // get other option menu
        //val settingActionMenuItem = menu.findItem(R.id.action_setting)
        if (searchActionMenuItem is MenuItem) {
            searchActionMenuItem.setOnActionExpandListener(object :
                MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    //make other options menu not visible
                    //settingActionMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    activity.invalidateOptionsMenu()
                    return true
                }
            })
        }
        val searchView = searchActionMenuItem.actionView
        if (searchView is SearchView) {
            searchView.apply {
                // Assumes current activity is the searchable activity
                val searchManager = context.getSystemService(Context.SEARCH_SERVICE)
                if (searchManager is SearchManager) {
                    setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
                }
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        // filter object based on query
                        //this@NoteListingFragment.listingAdapter.filter.filter(newText)
                        return true
                    }

                })

            }
        }
    }

}