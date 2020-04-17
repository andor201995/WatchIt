package com.andor.watchit.core.di.application

import android.app.Application
import com.andor.watchit.core.framework.RoomMovieDataSource
import com.andor.watchit.core.framework.db.DatabaseService
import com.andor.watchit.core.framework.db.MovieDao
import com.andor.watchit.repository.MovieDataSource
import com.andor.watchit.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun getMovieRepository(movieDataSource: MovieDataSource) = MovieRepository(movieDataSource)

    @Provides
    fun getDatabaseService(application: Application) = DatabaseService.getInstance(application)

    @Provides
    fun getMovieDao(databaseService: DatabaseService) = databaseService.getMovieDao()

    @Provides
    fun getMovieDataSource(movieDao: MovieDao): MovieDataSource = RoomMovieDataSource(movieDao)

}