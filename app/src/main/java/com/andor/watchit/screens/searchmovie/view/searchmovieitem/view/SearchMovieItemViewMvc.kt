package com.andor.watchit.screens.searchmovie.view.searchmovieitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.searchmovie.model.Event
import com.andor.watchit.usecase.common.model.GeneralMovie

interface SearchMovieItemViewMvc : ObservableViewMvc<Event> {
    fun bindItem(item: GeneralMovie)
    fun cleanUp()
}
