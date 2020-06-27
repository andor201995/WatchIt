package com.andor.watchit.screens.listdetail.model

sealed class DetailViewEvent {
    data class PosterClick(val detailModel: DetailUiModel) : DetailViewEvent()
    object PosterScrollToBack : DetailViewEvent()
}
