package com.andor.watchit.core

import android.app.Activity
import android.content.Intent
import com.andor.watchit.R

object ThemeUtils {
    private var sTheme = 0

    const val THEME_MATERIAL_DEFAULT = 0
    const val THEME_YOUR_CUSTOM_LIGHT = 1
    const val THEME_YOUR_CUSTOM_DARK = 2

    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
        activity.overridePendingTransition(
            R.anim.nav_default_enter_anim,
            R.anim.nav_default_exit_anim
        )
    }

    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            THEME_MATERIAL_DEFAULT -> activity.setTheme(R.style.AppTheme_Default)
            THEME_YOUR_CUSTOM_LIGHT -> activity.setTheme(R.style.AppTheme_Light)
            THEME_YOUR_CUSTOM_DARK -> activity.setTheme(R.style.AppTheme_Dark)
        }
    }
}
