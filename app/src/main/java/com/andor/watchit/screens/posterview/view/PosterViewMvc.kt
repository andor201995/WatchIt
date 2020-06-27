package com.andor.watchit.screens.posterview.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.posterview.model.Event

interface PosterViewMvc : ObservableViewMvc<Event> {
    fun setMoviePoster(detailUiModel: DetailUiModel)
}
