package com.andor.watchit.repository.tv

import androidx.sqlite.db.SimpleSQLiteQuery
import com.andor.watchit.repository.RepositoryUtils
import com.andor.watchit.repository.entity.TvDbContract
import com.andor.watchit.repository.entity.TvEntity
import com.andor.watchit.usecase.common.model.TvUiModel
import javax.inject.Inject

interface TvDataSource {
    suspend fun add(tvUiModel: TvUiModel)
    suspend fun addAll(tvUiModelList: List<TvUiModel>)
    suspend fun get(tvId: Double): TvUiModel?
    suspend fun getAll(): List<TvUiModel>
    suspend fun remove(tvUiModel: TvUiModel)
    suspend fun getPage(pageNumber: Int, pageSize: Int = 20): List<TvUiModel>
    suspend fun getCachedTvCount(): Int
    suspend fun getTvByName(query: String, pageNumber: Int): List<TvUiModel>
    suspend fun getSearchTvCount(query: String, pageNumber: Int): Int
}

class TvDataSourceImpl @Inject constructor(private val tvDao: TvDao) : TvDataSource {
    override suspend fun add(tvUiModel: TvUiModel) =
        tvDao.addTvEntity(TvEntity.fromTvUiModel(tvUiModel))

    override suspend fun addAll(tvUiModelList: List<TvUiModel>) =
        tvDao.addAllTvEntity(tvUiModelList.map { TvEntity.fromTvUiModel(it) })

    override suspend fun get(tvId: Double): TvUiModel? = tvDao.getTvEntity(tvId)?.toTvUiModel()

    override suspend fun getAll(): List<TvUiModel> = tvDao.getAllTvEntity().map { it.toTvUiModel() }

    override suspend fun remove(tvUiModel: TvUiModel) =
        tvDao.deleteTv(TvEntity.fromTvUiModel(tvUiModel))

    override suspend fun getPage(pageNumber: Int, pageSize: Int): List<TvUiModel> =
        tvDao.getPagedTv(pageNumber, pageSize).map { it.toTvUiModel() }

    override suspend fun getCachedTvCount(): Int = tvDao.getCount()

    override suspend fun getTvByName(query: String, pageNumber: Int): List<TvUiModel> {
        val queryArgs = query.split("+")

        val queryString =
            RepositoryUtils.constructSearchQuery(
                queryArgs,
                TvDbContract.TABLE_NAME,
                TvDbContract.ORIGINAL_TITLE
            ).append("\nORDER BY rating DESC LIMIT 20 OFFSET ($pageNumber-1)*20")

        val simpleSQLiteQuery = SimpleSQLiteQuery(queryString.toString())

        return tvDao.getTvEntityByQuery(simpleSQLiteQuery).map { it.toTvUiModel() }
    }

    override suspend fun getSearchTvCount(query: String, pageNumber: Int): Int {
        val queryArgs = query.split("+")

        val queryString =
            RepositoryUtils.constructSearchQuery(
                queryArgs, TvDbContract.TABLE_NAME,
                TvDbContract.ORIGINAL_TITLE
            ).insert(0, "SELECT COUNT(movie_id) FROM(\n").append("\n)")

        val simpleSQLiteQuery = SimpleSQLiteQuery(queryString.toString())

        return tvDao.getSearchTvCount(simpleSQLiteQuery)
    }
}
