package com.andor.watchit.core.di.screen

import androidx.fragment.app.Fragment
import com.andor.watchit.core.di.utils.ActivityScope
import com.andor.watchit.core.exception.DaggerException
import com.andor.watchit.screens.common.controller.BaseActivity
import com.andor.watchit.screens.common.controller.BaseFragment
import dagger.android.AndroidInjector
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class FragmentInjector @Inject constructor(
    private val fragmentInjectorFactoryMap: Map<Class<out Fragment>,
            @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Fragment>>>
) {
    private val cache: MutableMap<String, AndroidInjector<Fragment>> = mutableMapOf()

    companion object {
        fun getInstance(fragment: Fragment): FragmentInjector {
            return (fragment.requireActivity() as BaseActivity).getFragmentInjector()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun inject(fragment: Fragment) {
        if (fragment !is BaseFragment) {
            throw DaggerException("${fragment::class.simpleName} must extend BaseFragment")
        }
        if (cache.containsKey(fragment.getInstanceId())) {
            cache[fragment.getInstanceId()]?.inject(fragment)
            return
        }
        val injectorFactory =
            fragmentInjectorFactoryMap[fragment::class.java]?.get()
                    as AndroidInjector.Factory<Fragment>
        injectorFactory.create(fragment)?.let {
            cache[fragment.getInstanceId()] = it
            it.inject(fragment)
        }
    }

    fun clear(fragment: Fragment) {
        if (fragment !is BaseFragment) {
            throw DaggerException("${fragment::class.simpleName} must extend BaseFragment")
        }
        cache.remove(fragment.getInstanceId())
    }
}
