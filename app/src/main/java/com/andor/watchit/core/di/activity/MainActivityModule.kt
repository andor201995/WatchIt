package com.andor.watchit.core.di.activity

import com.andor.watchit.core.framework.UseCases
import com.andor.watchit.network.findmovie.FindMovieEndPoint
import com.andor.watchit.network.findmovie.FindMovieEndPointImpl
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPoint
import com.andor.watchit.network.topratedmovie.TopRatedMovieListEndPointImpl
import com.andor.watchit.network.tv.PopularTvEndPoint
import com.andor.watchit.network.tv.PopularTvEndPointImpl
import com.andor.watchit.usecase.findmovie.FindMovieDataSourceFactory
import com.andor.watchit.usecase.findmovie.FindMovieUseCase
import com.andor.watchit.usecase.findmovie.FindMovieUseCaseImpl
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSourceFactory
import com.andor.watchit.usecase.topratedmovie.TopRatedMoviePageDataSource
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCase
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieUseCaseImpl
import com.andor.watchit.usecase.tv.PopularTvUseCase
import com.andor.watchit.usecase.tv.PopularTvUseCaseImpl
import com.andor.watchit.usecase.tv.TvListPageDataSource
import com.andor.watchit.usecase.tv.TvListPageDataSourceFactory
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
        fun bindTopRatedMovieListUseCase(
            topRatedMovieUseCaseImpl: TopRatedMovieUseCaseImpl
        ): TopRatedMovieUseCase

        @Binds
        fun bindTopRatedMovieListEndPoint(
            topRatedMovieListEndPointImpl: TopRatedMovieListEndPointImpl
        ): TopRatedMovieListEndPoint

        @Binds
        fun bindPopularTvEntPoint(popularTvEndPointImpl: PopularTvEndPointImpl): PopularTvEndPoint

        @Binds
        fun bindPopularTvUseCase(popularTvUseCaseImpl: PopularTvUseCaseImpl): PopularTvUseCase
    }

    @Provides
    fun provideTopRatedMovieDataSourceFactory(
        topRatedMoviePageDataSource: TopRatedMoviePageDataSource
    ): TopRatedMovieDataSourceFactory =
        TopRatedMovieDataSourceFactory(
            topRatedMoviePageDataSource
        )

    @Provides
    fun provideTopRatedMovieDataSource(
        topRatedMovieUseCase: TopRatedMovieUseCase,
        executor: ExecutorService
    ): TopRatedMoviePageDataSource =
        TopRatedMoviePageDataSource(
            topRatedMovieUseCase,
            executor
        )

    @Provides
    fun provideUseCases(
        findMovieUseCase: FindMovieUseCase,
        topRatedMovieUseCase: TopRatedMovieUseCase,
        popularTvUseCase: PopularTvUseCase
    ) = UseCases(
        findMovieUseCase,
        topRatedMovieUseCase,
        popularTvUseCase
    )

    @Provides
    fun provideFindMovieDataSourceFactory(
        findMovieUseCase: FindMovieUseCase,
        executor: ExecutorService
    ): FindMovieDataSourceFactory =
        FindMovieDataSourceFactory(findMovieUseCase, executor)

    @Provides
    fun provideTvListPageDataSource(
        popularTvUseCase: PopularTvUseCase,
        executor: ExecutorService
    ) =
        TvListPageDataSource(
            popularTvUseCase,
            executor
        )

    @Provides
    fun provideTvListPageDataSourceFactory(
        tvListPageDataSource: TvListPageDataSource
    ) =
        TvListPageDataSourceFactory(
            tvListPageDataSource
        )
}
