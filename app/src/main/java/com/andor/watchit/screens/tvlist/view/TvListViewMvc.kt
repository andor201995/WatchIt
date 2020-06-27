package com.andor.watchit.screens.tvlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.andor.watchit.core.inVisible
import com.andor.watchit.core.visible
import com.andor.watchit.databinding.TvListFragmentBinding
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.Utils
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.tvlist.model.TvListEvent
import com.andor.watchit.screens.tvlist.view.listitem.TvListAdapter
import com.andor.watchit.usecase.common.model.TvUiModel

interface TvListViewMvc : ObservableViewMvc<TvListEvent> {
    fun updateList(listOfTvUiModel: PagedList<TvUiModel>)
    fun showLoader()
    fun hideLoader()
    fun showListLoadingError()
    fun showListLoadingCompleted()
    fun showListLoading()
}

class TvListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : TvListViewMvc, BaseObservableViewMvc<TvListEvent>() {

    private val binding = TvListFragmentBinding.inflate(layoutInflater, parent, false)
    private lateinit var adapter: TvListAdapter

    init {
        setRootView(binding.root)
        setUpRecyclerView(viewMvcFactory)
    }

    private fun setUpRecyclerView(viewMvcFactory: ViewMvcFactory) {
        binding.tvRecyclerView.apply {
            if (adapter == null) {
                this@TvListViewMvcImpl.adapter =
                    TvListAdapter(
                        viewMvcFactory,
                        getEventStream()
                    )

                adapter = this@TvListViewMvcImpl.adapter

                val gridCount = Utils.getPossibleGridCount(context)
                layoutManager = GridLayoutManager(context, gridCount)
                binding.loader.shimmerRecyclerView.layoutManager =
                    GridLayoutManager(context, gridCount)
            } else {
                this@TvListViewMvcImpl.adapter = adapter as TvListAdapter
            }
        }
    }

    override fun updateList(listOfTvUiModel: PagedList<TvUiModel>) {
        adapter.submitList(listOfTvUiModel)
    }

    override fun showLoader() {
        binding.loader.shimmerRecyclerView.showShimmer()
        binding.loader.root.visible()
    }

    override fun hideLoader() {
        binding.loader.shimmerRecyclerView.hideShimmer()
        binding.loader.root.inVisible()
    }

    override fun showListLoadingError() {
        adapter.setListLoadingState(TvListAdapter.ListLoading.Error)
    }

    override fun showListLoadingCompleted() {
        adapter.setListLoadingState(TvListAdapter.ListLoading.Completed)
    }

    override fun showListLoading() {
        adapter.setListLoadingState(TvListAdapter.ListLoading.Loading)
    }
}
