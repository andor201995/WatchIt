package com.andor.watchit.core

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.andor.watchit.core.di.activity.ActivityInjector
import com.andor.watchit.core.di.application.DaggerApplicationComponent
import com.andor.watchit.core.utils.ThemeUtils.THEME_KEY
import com.andor.watchit.core.utils.ThemeUtils.THEME_MATERIAL_DEFAULT
import com.andor.watchit.core.utils.ThemeUtils.THEME_SHARED_PREF_KEY
import javax.inject.Inject

class MainApplication : Application() {

    var themeInt: Int
        get() {
            return getSharedPreferences(THEME_SHARED_PREF_KEY, Context.MODE_PRIVATE)?.getInt(
                THEME_KEY,
                THEME_MATERIAL_DEFAULT
            ) ?: THEME_MATERIAL_DEFAULT
        }
        set(value) {
            getSharedPreferences(THEME_SHARED_PREF_KEY, Context.MODE_PRIVATE)?.edit()?.putInt(
                THEME_KEY,
                value
            )?.apply()
        }

    @Inject
    lateinit var injector: ActivityInjector

    override fun onCreate() {
        super.onCreate()
        val applicationComponent = DaggerApplicationComponent.factory().create(this)
        applicationComponent.inject(this)
    }

    fun getActivityInjector(): ActivityInjector {
        return injector
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
