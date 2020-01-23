package com.andor.watchit.screens.moviedetail.model

import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

sealed class Event {
    data class PosterClick(val movieDetail: TopRatedMovie) : Event()
    object PosterScrollToBack : Event()
}