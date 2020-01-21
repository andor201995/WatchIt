package com.andor.watchit.screens.topratedmovielist.model

import androidx.navigation.Navigator
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

sealed class Event {
    data class LoadMovie(val topRatedMovie: TopRatedMovie, val extra: Navigator.Extras) : Event()

    object RetryListLoading : Event()
}