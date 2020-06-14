package com.andor.watchit.screens.common.mvc

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat

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

    protected fun getInteger(@IntegerRes id: Int): Int {
        return context.resources.getInteger(id)
    }

    protected fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    protected fun getAttrColor(@AttrRes id: Int): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(id, typedValue, true)
        return typedValue.data
    }
}
