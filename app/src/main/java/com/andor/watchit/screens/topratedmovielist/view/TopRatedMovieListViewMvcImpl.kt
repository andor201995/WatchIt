package com.andor.watchit.screens.topratedmovielist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.andor.watchit.R
import com.andor.watchit.core.inVisible
import com.andor.watchit.core.visible
import com.andor.watchit.databinding.TopRatedMovieListFragmentBinding
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.Utils
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

    private var binding: TopRatedMovieListFragmentBinding =
        TopRatedMovieListFragmentBinding.inflate(layoutInflater, parent, false)
    private lateinit var topRatedMovieListAdapter: TopRatedMovieListAdapter

    init {
        setRootView(binding.root)
        setUpRecyclerView(viewMvcFactory)
    }

    private fun setUpRecyclerView(viewMvcFactory: ViewMvcFactory) {
        binding.topRatedMovieList.apply {
            if (adapter == null) {
                topRatedMovieListAdapter =
                    TopRatedMovieListAdapter(
                        viewMvcFactory,
                        getEventStream()
                    )

                adapter = topRatedMovieListAdapter

                val gridCount = Utils.getPossibleGridCount(context)
                layoutManager = GridLayoutManager(context, gridCount)
                binding.loader.shimmerRecyclerView.layoutManager =
                    GridLayoutManager(context, gridCount)
            } else {
                topRatedMovieListAdapter = adapter as TopRatedMovieListAdapter
            }
        }
    }

    override fun updateList(listOfGeneralMovie: PagedList<GeneralMovie>) {
        topRatedMovieListAdapter.submitList(listOfGeneralMovie)
    }

    override fun showLoader() {
        binding.loader.apply {
            shimmerRecyclerView.showShimmer()
            root.visible()
        }

    }

    override fun hideLoader() {
        binding.loader.apply {
            shimmerRecyclerView.hideShimmer()
            root.inVisible()
        }
    }

    override fun showListLoadingError() {
        topRatedMovieListAdapter.setListLoadingState(TopRatedMovieListAdapter.ListLoading.Error)
    }

    override fun showListLoadingCompleted() {
        topRatedMovieListAdapter.setListLoadingState(TopRatedMovieListAdapter.ListLoading.Completed)
    }

    override fun showListLoading() {
        topRatedMovieListAdapter.setListLoadingState(TopRatedMovieListAdapter.ListLoading.Loading)
    }

    override fun selectOptionItem(itemId: Int) {
        if (itemId == R.id.search) {
            getEventStream().onNext(Event.OpenSearchScreen)
        }
    }
}