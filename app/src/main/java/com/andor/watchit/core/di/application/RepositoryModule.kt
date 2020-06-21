package com.andor.watchit.core.di.application

import android.content.Context
import com.andor.watchit.core.framework.db.DatabaseService
import com.andor.watchit.repository.movie.MovieDataSource
import com.andor.watchit.repository.movie.MovieDataSourceImpl
import com.andor.watchit.repository.movie.MovieRepository
import com.andor.watchit.repository.tv.TvDataSource
import com.andor.watchit.repository.tv.TvDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RepositoryModule.Binding::class
    ]
)
class RepositoryModule {

    @Provides
    fun getMovieRepository(movieDataSource: MovieDataSource) =
        MovieRepository(movieDataSource)

    @Provides
    fun getDatabaseService(context: Context) = DatabaseService.getInstance(context)

    @Provides
    fun getMovieDao(databaseService: DatabaseService) = databaseService.getMovieDao()

    @Provides
    fun getTVDao(databaseService: DatabaseService) = databaseService.getTvDao()

    @Module
    internal interface Binding {
        @Binds
        fun getMovieDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource

        @Binds
        fun getTvDataSource(tvDataSourceImpl: TvDataSourceImpl): TvDataSource
    }
}
