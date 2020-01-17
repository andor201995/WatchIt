package com.andor.watchit.screens.topratedmovielist.model

import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

data class TopRatedMovieScreenState(
    val listOfTopRatedMovie: List<TopRatedMovie>,
    val status: ScreenStatus
)

enum class ScreenStatus {
    LOADING, FETCH_SUCCESS, FETCH_FAILED, IDEAL
}