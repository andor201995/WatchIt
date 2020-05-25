package com.andor.watchit.usecase.topratedmovie

import androidx.paging.DataSource
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class TopRatedMovieDataSourceFactory @Inject constructor(val topRatedMovieDataSource: TopRatedMovieDataSource) :
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