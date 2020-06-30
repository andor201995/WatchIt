package com.andor.watchit.core.utils

import android.app.Activity
import com.andor.watchit.R
import com.andor.watchit.core.MainApplication

object ThemeUtils {

    const val THEME_SHARED_PREF_KEY = "theme_pref"
    const val THEME_KEY = "theme_key"
    const val THEME_MATERIAL_DEFAULT = 0
    const val THEME_YOUR_CUSTOM_LIGHT = 1
    const val THEME_YOUR_CUSTOM_DARK = 2

    fun changeToTheme(activity: Activity, theme: Int) {
        (activity.application as MainApplication).themeInt = theme
        activity.recreate()
    }

    fun onActivityCreateSetTheme(activity: Activity) {
        activity.apply {
            when ((application as MainApplication).themeInt) {
                THEME_MATERIAL_DEFAULT -> theme.applyStyle(R.style.AppTheme_Default, true)
                THEME_YOUR_CUSTOM_LIGHT -> theme.applyStyle(R.style.AppTheme_Light, true)
                THEME_YOUR_CUSTOM_DARK -> theme.applyStyle(R.style.AppTheme_Dark, true)
            }
        }
    }
}
