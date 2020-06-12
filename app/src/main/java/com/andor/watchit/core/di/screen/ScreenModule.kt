package com.andor.watchit.core.di.screen

import android.view.LayoutInflater
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.main.MainActivity
import com.andor.watchit.screens.common.helper.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {

    @Provides
    fun provideViewMvcFactory(inflater: LayoutInflater, imageLoader: ImageLoader) =
        ViewMvcFactory(inflater, imageLoader)

    @Provides
    fun provideLayoutInflater(activity: MainActivity): LayoutInflater =
        LayoutInflater.from(activity)

    @Provides
    fun provideScreenNavigator() = ScreenNavigator()
}
