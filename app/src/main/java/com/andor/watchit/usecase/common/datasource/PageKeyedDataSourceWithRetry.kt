package com.andor.watchit.usecase.common.datasource

import androidx.paging.PageKeyedDataSource
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executor

abstract class PageKeyedDataSourceWithRetry<KEY, VALUE, INITIAL_EVENT, NETWORK_EVENT>(private val retryExecutor: Executor) :
    PageKeyedDataSource<KEY, VALUE>() {

    val initialNetworkStateStream: PublishSubject<INITIAL_EVENT> =
        PublishSubject.create()
    val nextNetworkStateStream: PublishSubject<NETWORK_EVENT> =
        PublishSubject.create()

    override fun loadBefore(params: LoadParams<KEY>, callback: LoadCallback<KEY, VALUE>) {
        // no need
    }

    // keep a function reference for the retry event
    protected var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }
}