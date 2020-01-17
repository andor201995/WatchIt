package com.andor.watchit.core.di.application

import android.app.Application
import com.andor.watchit.network.endpoints.TopRatedMovieListEndPoint
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideTopRatedMovieListUseCase(topRatedMovieListEndPoint: TopRatedMovieListEndPoint): TopRatedMovieUseCase {
        return TopRatedMovieUseCaseImpl(topRatedMovieListEndPoint)
    }
}