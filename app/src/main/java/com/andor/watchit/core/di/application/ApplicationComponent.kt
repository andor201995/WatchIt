package com.andor.watchit.core.di.application

import com.andor.watchit.core.di.presentation.PresentationComponent
import com.andor.watchit.core.di.presentation.PresentationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun getPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}