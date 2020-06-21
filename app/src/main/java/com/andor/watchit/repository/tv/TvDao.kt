package com.andor.watchit.repository.tv

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.andor.watchit.repository.entity.TvDbContract.RATING
import com.andor.watchit.repository.entity.TvDbContract.TABLE_NAME
import com.andor.watchit.repository.entity.TvDbContract.TV_ID
import com.andor.watchit.repository.entity.TvEntity

@Dao
interface TvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvEntity(tvEntity: TvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTvEntity(tvEntityList: List<TvEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE $TV_ID=:tvId")
    suspend fun getTvEntity(tvId: Double): TvEntity?

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $RATING")
    suspend fun getAllTvEntity(): List<TvEntity>

    @Query(
        """SELECT * FROM $TABLE_NAME ORDER BY $RATING
        DESC LIMIT :pageSize OFFSET (:pageNumber-1)*:pageSize"""
    )
    suspend fun getPagedTv(pageNumber: Int, pageSize: Int): List<TvEntity>

    @Delete
    suspend fun deleteTv(tvEntity: TvEntity)

    @Query("SELECT COUNT($TV_ID) FROM $TABLE_NAME")
    suspend fun getCount(): Int

    @RawQuery
    suspend fun getTvEntityByQuery(query: SimpleSQLiteQuery): List<TvEntity>

    @RawQuery
    suspend fun getSearchTvCount(query: SimpleSQLiteQuery): Int
}
