package com.andor.watchit.screens.topratedmovielist.model

import com.andor.watchit.usecase.common.model.GeneralMovie

sealed class MovieListEvent {
    data class LoadMovie(val generalMovie: GeneralMovie) : MovieListEvent()
    object RetryListLoading : MovieListEvent()
    object OpenSearchScreen : MovieListEvent()
}
