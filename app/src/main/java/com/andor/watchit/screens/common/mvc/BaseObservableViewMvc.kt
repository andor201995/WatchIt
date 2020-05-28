package com.andor.watchit.screens.common.mvc

import io.reactivex.subjects.PublishSubject

open class BaseObservableViewMvc<EVENT_TYPE> : ObservableViewMvc<EVENT_TYPE>, BaseViewMvc() {

    private val viewEventStream: PublishSubject<EVENT_TYPE> = PublishSubject.create()

    override fun getEventStream(): PublishSubject<EVENT_TYPE> {
        return viewEventStream
    }
}
