package com.andor.watchit.screens.tvlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.andor.watchit.R
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.BaseObservableViewMvc
import com.andor.watchit.screens.common.mvc.ObservableViewMvc
import com.andor.watchit.screens.tvlist.model.TvListEvent

interface TvListViewMvc : ObservableViewMvc<TvListEvent>

class TvListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : TvListViewMvc, BaseObservableViewMvc<TvListEvent>() {
    init {
        setRootView(layoutInflater.inflate(R.layout.tv_list_fragment, parent, false))
    }
}
