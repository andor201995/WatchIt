package com.andor.watchit.core

import android.app.Application
import com.andor.watchit.core.di.application.ApplicationComponent
import com.andor.watchit.core.di.application.ApplicationModule
import com.andor.watchit.core.di.application.DaggerApplicationComponent

class MainApplication : Application() {

    lateinit var mApplicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(
            ApplicationModule(this)
        ).build()
    }
}