package com.andor.watchit.screens.networkerror.view

import com.andor.watchit.screens.common.mvc.ObservableViewMvc

interface NetworkErrorViewMvc : ObservableViewMvc<NetworkErrorViewMvc.Event> {
    sealed class Event {
        object SwipeRefresh : Event()
    }
}
