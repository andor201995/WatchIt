package com.andor.watchit.screens.common.mvc

import io.reactivex.subjects.PublishSubject

interface ObservableViewMvc<EVENT_TYPE> : ViewMvc {
    fun getEventStream(): PublishSubject<EVENT_TYPE>
}
