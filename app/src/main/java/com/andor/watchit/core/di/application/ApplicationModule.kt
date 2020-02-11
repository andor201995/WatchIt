package com.andor.watchit.core.di.application

import android.app.Application
import com.andor.watchit.network.common.MovieApi
import com.andor.watchit.network.findmovie.FindMovieEndPoint
import com.andor.watchit.network.findmovie.FindMovieEndPointImpl
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPointImpl
import com.andor.watchit.screens.common.helper.GlideImageLoader
import com.andor.watchit.screens.common.helper.ImageLoader
import com.andor.watchit.usecase.findmovie.FindMovieDataSourceFactory
import com.andor.watchit.usecase.findmovie.FindMovieUseCase
import com.andor.watchit.usecase.findmovie.FindMovieUseCaseImpl
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSource
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSourceFactory
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideTopRatedMovieListUseCase(topRatedMovieListEndPoint: TopRatedMovieListEndPoint): TopRatedMovieUseCase {
        return TopRatedMovieUseCaseImpl(topRatedMovieListEndPoint)
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieListEndPoint(movieApi: MovieApi): TopRatedMovieListEndPoint {
        return TopRatedMovieListEndPointImpl(
            movieApi
        )
    }

    @Provides
    fun provideTopRatedMovieDataSourceFactory(topRatedMovieDataSource: TopRatedMovieDataSource): TopRatedMovieDataSourceFactory {
        return TopRatedMovieDataSourceFactory(
            topRatedMovieDataSource
        )
    }

    @Provides
    fun provideTopRatedMovieDataSource(
        topRatedMovieUseCase: TopRatedMovieUseCase,
        executor: ExecutorService
    ): TopRatedMovieDataSource {
        return TopRatedMovieDataSource(
            topRatedMovieUseCase,
            executor
        )
    }

    @Singleton
    @Provides
    fun provideFindMovieUseCase(findMovieEndPoint: FindMovieEndPoint): FindMovieUseCase {
        return FindMovieUseCaseImpl(findMovieEndPoint)
    }

    @Singleton
    @Provides
    fun provideFindMovieEndPoint(movieApi: MovieApi): FindMovieEndPoint {
        return FindMovieEndPointImpl(movieApi)
    }

    @Provides
    fun provideFindMovieDataSourceFactory(
        findMovieUseCase: FindMovieUseCase,
        executor: ExecutorService
    ): FindMovieDataSourceFactory {
        return FindMovieDataSourceFactory(findMovieUseCase, executor)
    }

    @Singleton
    @Provides
    fun provideExecutor(): ExecutorService {
        return Executors.newFixedThreadPool(5)
    }

    @Singleton
    @Provides
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader(application)
//        return PicassoImageLoader(application)
    }
}