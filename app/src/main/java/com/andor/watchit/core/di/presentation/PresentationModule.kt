package com.andor.watchit.core.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.andor.watchit.screens.common.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activity: Activity) {

    @Provides
    fun provideViewMvcFactory(inflater: LayoutInflater): ViewMvcFactory {
        return ViewMvcFactory(inflater)
    }

    @Provides
    fun provideLayoutInflator(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    fun provideContext(): Context {
        return activity
    }
}