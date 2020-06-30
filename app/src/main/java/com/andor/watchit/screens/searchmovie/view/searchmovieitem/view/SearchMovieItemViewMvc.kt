package com.andor.watchit.screens.searchmovie.view.searchmovieitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.SearchViewEvent
import com.andor.watchit.usecase.common.model.MovieUiModel

interface SearchMovieItemViewMvc : ObservableViewMvc<SearchViewEvent> {
    fun bindItem(item: MovieUiModel)
    fun cleanUp()
}
