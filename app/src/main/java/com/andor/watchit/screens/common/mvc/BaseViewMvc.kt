package com.andor.watchit.screens.common.mvc

import android.content.Context
import android.view.View

open class BaseViewMvc : ViewMvc {

    private lateinit var mRootView: View

    override fun getRootView(): View {
        return mRootView
    }

    protected fun setRootView(rootView: View) {
        mRootView = rootView
    }

    protected fun <T : View> findViewById(id: Int): T {
        return mRootView.findViewById(id)
    }

    protected val context: Context
        get() = getRootView().context

    protected fun getString(id: Int): String {
        return context.resources.getString(id)
    }

    protected fun getInteger(id: Int): Int {
        return context.resources.getInteger(id)
    }

    protected fun getColor(id: Int): Int {
        return context.resources.getInteger(id)
    }
}
