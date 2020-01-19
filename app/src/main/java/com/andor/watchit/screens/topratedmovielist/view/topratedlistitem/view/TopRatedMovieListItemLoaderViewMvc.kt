package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view

import com.andor.watchit.screens.common.mvc.ViewMvc

interface TopRatedMovieListItemLoaderViewMvc : ViewMvc {
    fun showLoader()
    fun showError()
    fun hideRow()
}