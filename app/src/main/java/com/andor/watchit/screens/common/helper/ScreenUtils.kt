package com.andor.watchit.screens.common.helper

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import androidx.paging.PagedList
import com.andor.watchit.R
import com.andor.watchit.core.rx.RxBaseObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

object ScreenUtils {
    fun getPossibleGridCount(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val dimension = context.resources.getDimensionPixelSize(R.dimen.itemWidth)
        val possibleGridCount = (outMetrics.widthPixels / dimension)
        return if (possibleGridCount > 2) possibleGridCount else 2
    }

    fun <I, N> bindNetworkStreamsAndNotify(
        firstPageLoadCallback: ((I) -> Unit)? = null,
        nextPageLoadCallback: ((N) -> Unit)? = null,
        firstSubject: BehaviorSubject<I>,
        nextSubject: BehaviorSubject<N>,
        compositeDisposable: CompositeDisposable
    ) {
        val initialNetworkStateObserver =
            object : RxBaseObserver<I>() {
                override fun onNext(t: I) {
                    firstPageLoadCallback?.invoke(t)
                }
            }

        val nextNetworkStateObserver =
            object : RxBaseObserver<N>() {
                override fun onNext(t: N) {
                    nextPageLoadCallback?.invoke(t)
                }
            }
        compositeDisposable.addAll(initialNetworkStateObserver, nextNetworkStateObserver)

        firstSubject.subscribeAsync(initialNetworkStateObserver)
        nextSubject.subscribeAsync(nextNetworkStateObserver)
    }

    private fun <T> BehaviorSubject<T>.subscribeAsync(
        observer: DisposableObserver<T>
    ) {
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    fun getPageListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setPrefetchDistance(60)
        .setPageSize(20)
        .setMaxSize(200)
        .build()
}
