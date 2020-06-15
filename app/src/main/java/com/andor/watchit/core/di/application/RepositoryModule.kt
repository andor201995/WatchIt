package com.andor.watchit.core.di.application

import android.content.Context
import com.andor.watchit.core.framework.db.DatabaseService
import com.andor.watchit.repository.movie.MovieDao
import com.andor.watchit.repository.movie.MovieDataSource
import com.andor.watchit.repository.movie.MovieRepository
import com.andor.watchit.repository.movie.RoomMovieDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun getMovieRepository(movieDataSource: MovieDataSource) =
        MovieRepository(movieDataSource)

    @Provides
    fun getDatabaseService(context: Context) = DatabaseService.getInstance(context)

    @Provides
    fun getMovieDao(databaseService: DatabaseService) = databaseService.getMovieDao()

    @Provides
    fun getMovieDataSource(movieDao: MovieDao): MovieDataSource =
        RoomMovieDataSource(movieDao)
}
