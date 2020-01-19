package com.andor.watchit.screens.topratedmovielist.model

import androidx.paging.PagedList
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

data class TopRatedMovieScreenState(
    val status: ScreenStatus
)

sealed class ScreenStatus {
    object LOADING : ScreenStatus()
    data class SUCCESS(val listOfTopRatedMovie: PagedList<TopRatedMovie>) : ScreenStatus()
    object FAILED : ScreenStatus()
}