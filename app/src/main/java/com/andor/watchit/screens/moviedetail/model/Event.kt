package com.andor.watchit.screens.moviedetail.model

import com.andor.watchit.usecase.common.model.GeneralMovie

sealed class Event {
    data class PosterClick(val movieDetail: GeneralMovie) : Event()
    object PosterScrollToBack : Event()
}