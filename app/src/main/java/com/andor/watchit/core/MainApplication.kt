package com.andor.watchit.core

import android.app.Application
import com.andor.watchit.core.di.activity.ActivityInjector
import com.andor.watchit.core.di.application.DaggerApplicationComponent
import javax.inject.Inject

class MainApplication : Application() {

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
}
