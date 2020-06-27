package com.andor.watchit.screens.listdetail.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.listdetail.model.DetailViewEvent

interface MovieDetailViewMvc : ObservableViewMvc<DetailViewEvent> {
    fun setDetails(detailModel: DetailUiModel)
}
