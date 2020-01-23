package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.usecase.common.model.GeneralMovie

interface TopRatedMovieListItemViewMvc : ObservableViewMvc<Event> {
    fun updateView(generalMovie: GeneralMovie)
}
