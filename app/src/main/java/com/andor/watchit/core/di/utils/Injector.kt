package com.andor.watchit.core.di.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.andor.watchit.core.di.activity.ActivityInjector
import com.andor.watchit.core.di.screen.FragmentInjector

object Injector {

    fun inject(activity: Activity) {
        ActivityInjector.getInstance(
            activity
        ).inject(activity)
    }

    fun clear(activity: Activity) {
        ActivityInjector.getInstance(
            activity
        ).clear(activity)
    }

    fun inject(fragment: Fragment) {
        FragmentInjector.getInstance(
            fragment
        ).inject(fragment)
    }

    fun clear(fragment: Fragment) {
        FragmentInjector.getInstance(
            fragment
        ).clear(fragment)
    }
}
