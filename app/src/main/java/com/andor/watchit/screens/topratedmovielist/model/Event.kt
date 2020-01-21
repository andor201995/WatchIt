package com.andor.watchit.screens.topratedmovielist.model

import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

sealed class Event {
    data class LoadMovie(val topRatedMovie: TopRatedMovie) :Event()
    object RetryListLoading : Event()
}