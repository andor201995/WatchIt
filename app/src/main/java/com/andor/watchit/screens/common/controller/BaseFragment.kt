package com.andor.watchit.screens.common.controller

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.di.application.ApplicationComponent
import com.andor.watchit.core.di.presentation.PresentationComponent
import com.andor.watchit.core.di.presentation.PresentationModule

open class BaseFragment : Fragment() {

    private var mIsInjectorUsed: Boolean = false

    @get:UiThread
    protected val presentationComponent: PresentationComponent
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent
                .getPresentationComponent(PresentationModule(activity!!))
        }

    private val applicationComponent: ApplicationComponent
        get() = (activity!!.application as MainApplication).mApplicationComponent
}