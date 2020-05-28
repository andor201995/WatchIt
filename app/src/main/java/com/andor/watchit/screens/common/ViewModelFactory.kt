package com.andor.watchit.screens.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    private val mProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>> = providerMap

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mProviderMap[modelClass]!!.get() as T
    }
}
