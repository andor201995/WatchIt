package com.andor.watchit.core.di.activity

import android.app.Activity
import android.content.Context
import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.exception.DaggerException
import com.andor.watchit.screens.common.controller.BaseActivity
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider

class ActivityInjector @Inject constructor(
    private val activityInjectorFactoryMap: Map<Class<out Activity>, @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>
) {
    companion object {
        fun getInstance(context: Context) =
            (context.applicationContext as MainApplication).getActivityInjector()
    }

    private val cache: MutableMap<String, AndroidInjector<out Activity>> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    fun inject(activity: Activity) {
        if (activity !is BaseActivity) {
            throw DaggerException("Activity is not extending BaseActivity")
        }
        if (cache.containsKey(activity.getInstanceId())) {
            (cache[activity.getInstanceId()] as AndroidInjector<Activity>).inject(activity)
            return
        }

        val injectorFactory =
            activityInjectorFactoryMap[activity::class.java]?.get() as AndroidInjector.Factory<Activity>
        injectorFactory.create(activity)?.let {
            cache[activity.getInstanceId()] = it
            it.inject(activity)
        }
    }

    /*
    * call when activity is finishing
    * */
    fun clear(activity: Activity) {
        if (activity !is BaseActivity) {
            throw DaggerException("Activity is not extending BaseActivity")
        }
        cache.remove(activity.getInstanceId())
    }
}
