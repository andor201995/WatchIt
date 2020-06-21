package com.andor.watchit.repository.tv

import com.andor.watchit.usecase.common.model.TvUiModel

class TvRepository(private val dataSource: TvDataSource) {
    suspend fun addTv(tvUiModel: TvUiModel) = dataSource.add(tvUiModel)

    suspend fun addAllTv(tvUiModelList: List<TvUiModel>) =
        dataSource.addAll(tvUiModelList)

    suspend fun getTv(tvId: Double) = dataSource.get(tvId)

    suspend fun getAllTv() = dataSource.getAll()

    suspend fun getPagedTv(pageNumber: Int) = dataSource.getPage(pageNumber)

    suspend fun removeTv(tvUiModel: TvUiModel) = dataSource.remove(tvUiModel)

    suspend fun getTvCount(): Int = dataSource.getCachedTvCount()

    suspend fun getTvByName(query: String, pageNumber: Int): List<TvUiModel> =
        dataSource.getTvByName(query, pageNumber)

    suspend fun getSearchCount(query: String, page: Int): Int =
        dataSource.getSearchTvCount(query, page)
}
