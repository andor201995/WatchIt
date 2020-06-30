package com.andor.watchit.usecase.movie

import androidx.paging.DataSource
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class TopRatedMovieDataSourceFactory @Inject constructor(
    val topRatedMoviePageDataSource: TopRatedMoviePageDataSource
) : DataSource.Factory<Long, GeneralMovie>() {

    private val mPageDataSourceRelay: PublishSubject<TopRatedMoviePageDataSource> =
        PublishSubject.create()

    override fun create(): DataSource<Long, GeneralMovie> {
        mPageDataSourceRelay.onNext(topRatedMoviePageDataSource)
        return topRatedMoviePageDataSource
    }

    fun getDataSourceStream(): PublishSubject<TopRatedMoviePageDataSource> {
        return mPageDataSourceRelay
    }
}
