package com.andor.watchit.core.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activity: Activity) {

    @Provides
    fun provideViewMvcFactory(inflater: LayoutInflater, imageLoader: ImageLoader): ViewMvcFactory {
        return ViewMvcFactory(inflater, imageLoader)
    }

    @Provides
    fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideScreenNavigator(activity: Activity): ScreenNavigator {
        return ScreenNavigator(activity)
    }
}