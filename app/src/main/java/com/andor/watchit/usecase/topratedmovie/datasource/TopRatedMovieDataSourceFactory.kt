package com.andor.watchit.usecase.topratedmovie.datasource

import androidx.paging.DataSource
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie
import io.reactivex.subjects.PublishSubject


class TopRatedMovieDataSourceFactory(val topRatedMovieDataSource: TopRatedMovieDataSource) :
    DataSource.Factory<Long, TopRatedMovie>() {

    private val mDataSourceRelay: PublishSubject<TopRatedMovieDataSource> = PublishSubject.create()

    override fun create(): DataSource<Long, TopRatedMovie> {
        mDataSourceRelay.onNext(topRatedMovieDataSource)
        return topRatedMovieDataSource
    }

    fun getDataSourceStream(): PublishSubject<TopRatedMovieDataSource> {
        return mDataSourceRelay
    }

}