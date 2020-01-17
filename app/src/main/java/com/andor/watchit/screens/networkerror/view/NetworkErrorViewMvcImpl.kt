package com.andor.watchit.screens.networkerror.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.andor.watchit.R
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc

class NetworkErrorViewMvcImpl(
    parent: ViewGroup?,
    inflater: LayoutInflater
) : NetworkErrorViewMvc, BaseObservableViewMvc<NetworkErrorViewMvc.Event>() {

    init {
        setRootView(inflater.inflate(R.layout.network_error_fragment, parent, false))
        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.errorSwipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            this.getEventStream().onNext(NetworkErrorViewMvc.Event.SwipeRefresh)
        }
    }
}