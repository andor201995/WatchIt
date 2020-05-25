package com.andor.watchit.core.di.activity

import com.andor.watchit.core.framework.UseCases
import com.andor.watchit.network.findmovie.FindMovieEndPoint
import com.andor.watchit.network.findmovie.FindMovieEndPointImpl
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPointImpl
import com.andor.watchit.usecase.findmovie.FindMovieDataSourceFactory
import com.andor.watchit.usecase.findmovie.FindMovieUseCase
import com.andor.watchit.usecase.findmovie.FindMovieUseCaseImpl
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSource
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSourceFactory
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService

@Module(
    includes = [
        MainActivityModule.Binding::class
    ]
)
class MainActivityModule {

    @Module
    internal interface Binding {
        @Binds
        fun bindFindMovieEndPoint(findMovieEndPointImpl: FindMovieEndPointImpl): FindMovieEndPoint

        @Binds
        fun bindFindMovieUseCase(findMovieUseCaseImpl: FindMovieUseCaseImpl): FindMovieUseCase

        @Binds
        fun bindTopRatedMovieListUseCase(topRatedMovieUseCaseImpl: TopRatedMovieUseCaseImpl): TopRatedMovieUseCase

        @Binds
        fun bindTopRatedMovieListEndPoint(topRatedMovieListEndPointImpl: TopRatedMovieListEndPointImpl): TopRatedMovieListEndPoint
    }

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
    fun provideUseCases(
        findMovieUseCase: FindMovieUseCase,
        topRatedMovieUseCase: TopRatedMovieUseCase
    ) = UseCases(
        findMovieUseCase,
        topRatedMovieUseCase
    )

    @Provides
    fun provideFindMovieDataSourceFactory(
        findMovieUseCase: FindMovieUseCase,
        executor: ExecutorService
    ): FindMovieDataSourceFactory =
        FindMovieDataSourceFactory(findMovieUseCase, executor)
}