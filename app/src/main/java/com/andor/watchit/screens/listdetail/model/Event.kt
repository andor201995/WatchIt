package com.andor.watchit.screens.listdetail.model

sealed class Event {
    data class PosterClick(val detailModel: DetailUiModel) : Event()
    object PosterScrollToBack : Event()
}
