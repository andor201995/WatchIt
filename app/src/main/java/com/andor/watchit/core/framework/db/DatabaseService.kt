package com.andor.watchit.core.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andor.watchit.repository.entity.MovieEntity
import com.andor.watchit.repository.entity.TvEntity
import com.andor.watchit.repository.movie.MovieDao
import com.andor.watchit.repository.tv.TvDao

@Database(
    entities = [
        MovieEntity::class,
        TvEntity::class
    ], version = 1
)
abstract class DatabaseService : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "watchit.db"
        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun getMovieDao(): MovieDao
    abstract fun getTvDao(): TvDao
}
