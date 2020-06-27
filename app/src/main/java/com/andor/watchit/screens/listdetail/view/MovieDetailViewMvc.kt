package com.andor.watchit.screens.listdetail.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.listdetail.model.Event

interface MovieDetailViewMvc : ObservableViewMvc<Event> {
    fun setDetails(detailModel: DetailUiModel)
}
