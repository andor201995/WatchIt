package com.andor.watchit.usecase.common.datasource

import androidx.paging.DataSource
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.topratedmovie.TopRatedMovieDataSource
import io.reactivex.subjects.PublishSubject


class TopRatedMovieDataSourceFactory(val topRatedMovieDataSource: TopRatedMovieDataSource) :
    DataSource.Factory<Long, GeneralMovie>() {

    private val mDataSourceRelay: PublishSubject<TopRatedMovieDataSource> = PublishSubject.create()

    override fun create(): DataSource<Long, GeneralMovie> {
        mDataSourceRelay.onNext(topRatedMovieDataSource)
        return topRatedMovieDataSource
    }

    fun getDataSourceStream(): PublishSubject<TopRatedMovieDataSource> {
        return mDataSourceRelay
    }

}