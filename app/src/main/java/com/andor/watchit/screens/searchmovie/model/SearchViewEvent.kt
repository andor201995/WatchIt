package com.andor.watchit.screens.searchmovie.model

import com.andor.watchit.usecase.common.model.MovieUiModel

sealed class SearchViewEvent {
    object SearchCollapse : SearchViewEvent()
    object HideLoader : SearchViewEvent()

    data class FindMovie(val query: String) : SearchViewEvent()
    data class OpenMovie(val movieUiModel: MovieUiModel) : SearchViewEvent()
}
