package com.andor.watchit.core.di.activity

import com.andor.watchit.core.framework.UseCases
import com.andor.watchit.network.endpoints.tv.PopularTvEndPoint
import com.andor.watchit.network.endpoints.tv.PopularTvEndPointImpl
import com.andor.watchit.network.movie.TopRatedMovieListEndPoint
import com.andor.watchit.network.movie.TopRatedMovieListEndPointImpl
import com.andor.watchit.network.search.FindMovieEndPoint
import com.andor.watchit.network.search.FindMovieEndPointImpl
import com.andor.watchit.usecase.movie.TopRatedMovieDataSourceFactory
import com.andor.watchit.usecase.movie.TopRatedMoviePageDataSource
import com.andor.watchit.usecase.movie.TopRatedMovieUseCase
import com.andor.watchit.usecase.movie.TopRatedMovieUseCaseImpl
import com.andor.watchit.usecase.search.FindMovieDataSourceFactory
import com.andor.watchit.usecase.search.FindMovieUseCase
import com.andor.watchit.usecase.search.FindMovieUseCaseImpl
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
