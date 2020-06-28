package com.andor.watchit.screens.tvlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.R
import com.andor.watchit.core.gone
import com.andor.watchit.core.visible
import com.andor.watchit.databinding.ListItemLoaderBinding
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.tvlist.model.TvListEvent

interface TvListItemLoaderViewMvc :
    ObservableViewMvc<TvListEvent> {
    fun showLoader()
    fun showError()
    fun hideRow()
}

class TvListItemLoaderViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater
) : TvListItemLoaderViewMvc, BaseObservableViewMvc<TvListEvent>() {
    private val binding = ListItemLoaderBinding.inflate(inflater, parent, false)

    init {
        setRootView(inflater.inflate(R.layout.list_item_loader, parent, false))

        binding.retryContainer.setOnClickListener {
            getEventStream()
                .onNext(TvListEvent.RetryListLoading)
        }
    }

    override fun showLoader() {
        binding.itemLoader.show()
        binding.itemLoader.visible()
        binding.retryContainer.gone()
    }

    override fun showError() {
        binding.retryContainer.visible()
        binding.itemLoader.hide()
    }

    override fun hideRow() {
        binding.loaderItemContainer.gone()
    }
}
