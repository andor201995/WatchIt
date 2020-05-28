package com.andor.watchit.screens.common.helper

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.andor.watchit.R

object Utils {
    fun getPossibleGridCount(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val dimension = context.resources.getDimensionPixelSize(R.dimen.itemWidth)
        val possibleGridCount = (outMetrics.widthPixels / dimension)
        return if (possibleGridCount > 2) possibleGridCount else 2
    }
}
