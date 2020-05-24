package com.andor.watchit.core

import android.app.Application
import com.andor.watchit.core.di.activity.ActivityInjector
import com.andor.watchit.core.di.application.ApplicationComponent
import com.andor.watchit.core.di.application.ApplicationModule
import com.andor.watchit.core.di.application.DaggerApplicationComponent
import javax.inject.Inject

class MainApplication : Application() {

    lateinit var mApplicationComponent: ApplicationComponent
        private set

    @Inject
    lateinit var injector: ActivityInjector

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
        mApplicationComponent.inject(this)
    }

    fun getActivityInjector(): ActivityInjector {
        return injector
    }
}