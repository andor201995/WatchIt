package com.andor.watchit.usecase.search

import androidx.paging.DataSource
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.Executor

class FindMovieDataSourceFactory(
    private val useCase: FindMovieUseCase,
    private val executor: Executor
) :
    DataSource.Factory<Long, GeneralMovie>() {
    private val mDataSourceRelay: BehaviorSubject<FindMovieDataSource> = BehaviorSubject.create()

    var query: String = ""

    override
    fun create(): DataSource<Long, GeneralMovie> {
        val dataSource = FindMovieDataSource(useCase, query, executor)
        mDataSourceRelay.onNext(dataSource)
        return dataSource
    }

    fun getDataSourceStream(): BehaviorSubject<FindMovieDataSource> {
        return mDataSourceRelay
    }
}
