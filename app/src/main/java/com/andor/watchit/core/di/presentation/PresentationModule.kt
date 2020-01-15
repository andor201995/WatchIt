package com.andor.watchit.core.di.presentation

import android.app.Activity
import com.andor.watchit.screens.topratedmovielist.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activity: Activity) {

    @Provides
    fun provideViewMvcFactory(): ViewMvcFactory {
        return ViewMvcFactory()
    }
}