package com.andor.watchit.screens.movielist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.andor.watchit.screens.movielist.controller.MovieListAdapter
import com.andor.watchit.screens.movielist.model.MovieListEvent
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.todkars.shimmer.ShimmerRecyclerView

interface TopRatedMovieListViewMvc :
    ObservableViewMvc<MovieListEvent> {
    fun updateList(listOfMovieUiModel: PagedList<MovieUiModel>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
    fun selectOptionItem(itemId: Int)
}

class TopRatedMovieListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<MovieListEvent>(),
    TopRatedMovieListViewMvc {

    private var shimmerRecyclerView: ShimmerRecyclerView
    private var loader: View
    private var recyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter

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
                MovieListAdapter(
                    viewMvcFactory,
                    getEventStream()
                )

            recyclerView.adapter = this.adapter

            val gridCount = ScreenUtils.getPossibleGridCount(context)
            recyclerView.layoutManager = GridLayoutManager(context, gridCount)
            shimmerRecyclerView.layoutManager = GridLayoutManager(context, gridCount)
        } else {
            adapter = recyclerView.adapter as MovieListAdapter
        }
    }

    override fun updateList(listOfMovieUiModel: PagedList<MovieUiModel>) {
        adapter.submitList(listOfMovieUiModel)
    }

    override fun showLoader() {
        loader.visible()
        shimmerRecyclerView.showShimmer()
    }

    override fun hideLoader() {
        loader.gone()
        shimmerRecyclerView.hideShimmer()
    }

    override fun showListLoadingError() {
        adapter.setListLoadingState(MovieListAdapter.ListLoading.Error)
    }

    override fun showListLoadingCompleted() {
        adapter.setListLoadingState(MovieListAdapter.ListLoading.Completed)
    }

    override fun showListLoading() {
        adapter.setListLoadingState(MovieListAdapter.ListLoading.Loading)
    }

    override fun selectOptionItem(itemId: Int) {
        if (itemId == R.id.search) {
            getEventStream().onNext(MovieListEvent.OpenSearchScreen)
        }
    }
}
