package com.andor.watchit.screens.tvlist.model

import com.andor.watchit.usecase.common.model.TvUiModel

sealed class TvListEvent {
    data class LoadTv(val tvUiModel: TvUiModel) : TvListEvent()
    object RetryListLoading : TvListEvent()
}
