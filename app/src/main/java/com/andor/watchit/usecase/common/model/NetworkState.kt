package com.andor.watchit.usecase.common.model

sealed class NetworkState {
    sealed class Initial : NetworkState() {
        object Loading : Initial()
        object Error : Initial()
        data class Success(val totalResult: Int) : Initial()
    }

    sealed class Next : NetworkState() {
        object Loading : Next()
        object Error : Next()
        object Success : Next()
        object Completed : Next()
    }
}
