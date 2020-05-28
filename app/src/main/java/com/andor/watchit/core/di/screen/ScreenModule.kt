package com.andor.watchit.core.di.screen

import android.content.Context
import android.view.LayoutInflater
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.helper.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {

    @Provides
    fun provideViewMvcFactory(inflater: LayoutInflater, imageLoader: ImageLoader) =
        ViewMvcFactory(inflater, imageLoader)

    @Provides
    fun provideLayoutInflater(context: Context): LayoutInflater = LayoutInflater.from(context)

    @Provides
    fun provideScreenNavigator() = ScreenNavigator()
}
