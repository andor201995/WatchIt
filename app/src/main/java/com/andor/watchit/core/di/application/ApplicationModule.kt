package com.andor.watchit.core.di.application

import android.app.Application
import com.andor.watchit.network.endpoints.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSource
import com.andor.watchit.usecase.topratedmovie.datasource.TopRatedMovieDataSourceFactory
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideTopRatedMovieListUseCase(topRatedMovieListEndPoint: TopRatedMovieListEndPoint): TopRatedMovieUseCase {
        return TopRatedMovieUseCaseImpl(topRatedMovieListEndPoint)
    }

    @Provides
    fun provideTopRatedMovieDataSourceFactory(topRatedMovieDataSource: TopRatedMovieDataSource): TopRatedMovieDataSourceFactory {
        return TopRatedMovieDataSourceFactory(topRatedMovieDataSource)
    }

    @Provides
    fun provideTopRatedMovieDataSource(topRatedMovieUseCase: TopRatedMovieUseCase): TopRatedMovieDataSource {
        val executor = Executors.newFixedThreadPool(5)
        return TopRatedMovieDataSource(topRatedMovieUseCase, executor)
    }
}