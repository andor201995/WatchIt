package com.andor.watchit.screens.topratedmovielist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.core.inVisible
import com.andor.watchit.core.visible
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.Utils
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller.TopRatedMovieListAdapter
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.todkars.shimmer.ShimmerRecyclerView


class TopRatedMovieListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<Event>(),
    TopRatedMovieListViewMvc {

    private var shimmerRecyclerView: ShimmerRecyclerView
    private var loader: View
    private var recyclerView: RecyclerView
    private lateinit var adapter: TopRatedMovieListAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.top_rated_movie_list_fragment, parent, false))
        recyclerView = findViewById(R.id.top_rated_movie_list)
        loader = findViewById(R.id.loader)
        shimmerRecyclerView = findViewById(R.id.shimmer_recycler_view)
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

            val gridCount = Utils.getPossibleGridCount(context)
            recyclerView.layoutManager = GridLayoutManager(context, gridCount)
            shimmerRecyclerView.layoutManager = GridLayoutManager(context, gridCount)
        } else {
            adapter = recyclerView.adapter as TopRatedMovieListAdapter
        }
    }

    override fun updateList(listOfGeneralMovie: PagedList<GeneralMovie>) {
        adapter.submitList(listOfGeneralMovie)
    }

    override fun showLoader() {
        shimmerRecyclerView.showShimmer()
        loader.visible()
    }

    override fun hideLoader() {
        shimmerRecyclerView.hideShimmer()
        loader.inVisible()
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