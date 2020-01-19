package com.andor.watchit.core

import io.reactivex.observers.DisposableObserver

abstract class RxBaseObserver<T> : DisposableObserver<T>() {
    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}