package com.andor.watchit.screens.topratedmovielist.view

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.widget.ContentLoadingProgressBar
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.core.visible
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller.TopRatedMovieListAdapter
import com.andor.watchit.usecase.common.model.GeneralMovie


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

    override fun selectOptionItem(itemId: Int) {
        if (itemId == R.id.search) {
            getEventStream().onNext(Event.OpenSearchScreen)
        }
    }
}