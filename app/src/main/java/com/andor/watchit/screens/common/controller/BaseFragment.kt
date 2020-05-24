package com.andor.watchit.screens.common.controller

import android.os.Bundle
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.di.application.ApplicationComponent
import com.andor.watchit.core.di.screen.ScreenComponent
import com.andor.watchit.core.di.screen.ScreenModule
import java.util.*

abstract class BaseFragment : Fragment() {

    private var injected: Boolean = false

    companion object {
        const val INSTANCE_ID_KEY = "instance_Id_fragment"
    }

    private lateinit var instanceId: String

    @get:UiThread
    protected val screenComponent: ScreenComponent
        get() {
            return applicationComponent
                .getPresentationComponent(ScreenModule(activity!!))
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        instanceId =
            savedInstanceState?.getString(BaseActivity.INSTANCE_ID_KEY)?.let { return@let it }
                ?: UUID.randomUUID().toString()

//        if (!injected) {
//            Injector.inject(this)
//            injected = true
//        }
    }

//    override fun onDestroyView() {
//        Injector.clear(this)
//        super.onDestroyView()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INSTANCE_ID_KEY, instanceId)
    }

    internal fun getInstanceId() = instanceId

    private val applicationComponent: ApplicationComponent
        get() = (activity!!.application as MainApplication).mApplicationComponent
}