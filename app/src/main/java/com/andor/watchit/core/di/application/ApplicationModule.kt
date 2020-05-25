package com.andor.watchit.core.di.application

import android.content.Context
import com.andor.watchit.screens.common.helper.GlideImageLoader
import com.andor.watchit.screens.common.helper.ImageLoader
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
class ApplicationModule {
    @Provides
    fun provideExecutor(): ExecutorService = Executors.newFixedThreadPool(5)

    @Provides
    fun provideImageLoader(context: Context): ImageLoader {
        return GlideImageLoader(context)
//        return PicassoImageLoader(application)
    }
}