package com.andor.watchit.core.rx

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

abstract class RxBaseSingleObserver<T> : SingleObserver<T> {
    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
