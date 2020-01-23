package com.andor.watchit.screens.posterview.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.posterview.model.Event
import com.andor.watchit.usecase.common.datasource.GeneralMovie

interface PosterViewMvc : ObservableViewMvc<Event> {
    fun setMoviePoster(movieDetail: GeneralMovie)
}