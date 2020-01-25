package com.andor.watchit.usecase.findmovie

import androidx.paging.DataSource
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executor

class FindMovieDataSourceFactory(
    private val useCase: FindMovieUseCase,
    private val executor: Executor
) :
    DataSource.Factory<Long, GeneralMovie>() {
    private val mDataSourceRelay: PublishSubject<FindMovieDataSource> = PublishSubject.create()

    var query: String = ""

    override
    fun create(): DataSource<Long, GeneralMovie> {
        val dataSource = FindMovieDataSource(useCase, query, executor)
        mDataSourceRelay.onNext(dataSource)
        return dataSource
    }

    fun getDataSourceStream(): PublishSubject<FindMovieDataSource> {
        return mDataSourceRelay
    }
}