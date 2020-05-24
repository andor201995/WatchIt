package com.andor.watchit.core.di.application

import android.content.Context
import com.andor.watchit.core.framework.UseCases
import com.andor.watchit.network.common.MovieApi
import com.andor.watchit.network.findmovie.FindMovieEndPoint
import com.andor.watchit.network.findmovie.FindMovieEndPointImpl
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPointImpl
import com.andor.watchit.repository.MovieRepository
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

@Module
class ApplicationModule(@get:Provides val application: Context) {

    @Provides
    fun provideTopRatedMovieListUseCase(
        topRatedMovieListEndPoint: TopRatedMovieListEndPoint,
        repository: MovieRepository
    ): TopRatedMovieUseCase =
        TopRatedMovieUseCaseImpl(topRatedMovieListEndPoint, repository)


    @Provides
    fun provideTopRatedMovieListEndPoint(movieApi: MovieApi): TopRatedMovieListEndPoint =
        TopRatedMovieListEndPointImpl(
            movieApi
        )


    @Provides
    fun provideTopRatedMovieDataSourceFactory(
        topRatedMovieDataSource: TopRatedMovieDataSource
    ): TopRatedMovieDataSourceFactory =
        TopRatedMovieDataSourceFactory(
            topRatedMovieDataSource
        )


    @Provides
    fun provideTopRatedMovieDataSource(
        topRatedMovieUseCase: TopRatedMovieUseCase,
        executor: ExecutorService
    ): TopRatedMovieDataSource =
        TopRatedMovieDataSource(
            topRatedMovieUseCase,
            executor
        )

    @Provides
    fun provideFindMovieUseCase(
        findMovieEndPoint: FindMovieEndPoint,
        repository: MovieRepository
    ): FindMovieUseCase =
        FindMovieUseCaseImpl(findMovieEndPoint, repository)

    @Provides
    fun provideFindMovieEndPoint(movieApi: MovieApi): FindMovieEndPoint =
        FindMovieEndPointImpl(movieApi)


    @Provides
    fun provideFindMovieDataSourceFactory(
        findMovieUseCase: FindMovieUseCase,
        executor: ExecutorService
    ): FindMovieDataSourceFactory =
        FindMovieDataSourceFactory(findMovieUseCase, executor)


    @Provides
    fun provideExecutor(): ExecutorService = Executors.newFixedThreadPool(5)


    @Provides
    fun provideImageLoader(context: Context): ImageLoader {
        return GlideImageLoader(context)
//        return PicassoImageLoader(application)
    }

    @Provides
    fun provideUseCases(
        findMovieUseCase: FindMovieUseCase,
        topRatedMovieUseCase: TopRatedMovieUseCase
    ) = UseCases(
        findMovieUseCase,
        topRatedMovieUseCase
    )
}