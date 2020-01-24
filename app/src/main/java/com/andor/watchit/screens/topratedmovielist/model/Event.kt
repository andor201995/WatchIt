package com.andor.watchit.screens.topratedmovielist.model

import com.andor.watchit.usecase.common.model.GeneralMovie

sealed class Event {
    data class LoadMovie(val generalMovie: GeneralMovie) : Event()
    object RetryListLoading : Event()
    object OpenSearchScreen : Event()
}