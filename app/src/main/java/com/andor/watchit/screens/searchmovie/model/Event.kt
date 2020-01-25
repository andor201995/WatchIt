package com.andor.watchit.screens.searchmovie.model

import com.andor.watchit.usecase.common.model.GeneralMovie

sealed class Event {
    object SearchCollapse : Event()
    data class FindMovie(val query: String) : Event()
    data class OpenMovie(val generalMovie: GeneralMovie) : Event()
}
