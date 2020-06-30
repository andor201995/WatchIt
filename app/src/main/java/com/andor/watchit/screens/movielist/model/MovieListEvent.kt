package com.andor.watchit.screens.movielist.model

import com.andor.watchit.usecase.common.model.MovieUiModel

sealed class MovieListEvent {
    data class LoadMovie(val movieUiModel: MovieUiModel) : MovieListEvent()
    object RetryListLoading : MovieListEvent()
    object OpenSearchScreen : MovieListEvent()
    object HideLoader : MovieListEvent()
}
