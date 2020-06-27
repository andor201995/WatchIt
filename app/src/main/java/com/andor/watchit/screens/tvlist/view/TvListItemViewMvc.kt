package com.andor.watchit.screens.tvlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.databinding.ListItemBinding
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.tvlist.model.TvListEvent
import com.andor.watchit.usecase.common.model.TvUiModel
import com.jakewharton.rxbinding3.view.clicks

interface TvListItemViewMvc : ObservableViewMvc<TvListEvent> {
    fun updateView(tvUiModel: TvUiModel)
    fun cleanUp()
}

class TvListItemViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater,
    private val imageLoader: ImageLoader
) :
    TvListItemViewMvc,
    BaseObservableViewMvc<TvListEvent>() {

    private val binding = ListItemBinding.inflate(inflater, parent, false)

    init {
        setRootView(binding.root)
    }

    override fun updateView(tvUiModel: TvUiModel) {
        binding.posterContainer.clicks().map {
            TvListEvent.LoadTv(tvUiModel)
        }.subscribe(getEventStream())

        binding.posterImageView.also {
            imageLoader.loadImageInto(it, tvUiModel.posterPath)
        }
    }

    override fun cleanUp() {
        imageLoader.cleanUp(binding.posterImageView)
    }
}
