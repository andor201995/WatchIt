package com.andor.watchit.screens.moviedetail.model

import androidx.navigation.Navigator
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

sealed class Event {
    data class PosterClick(val movieDetail: TopRatedMovie, val extra: Navigator.Extras) : Event()
    object PosterScrollToBack : Event()
}