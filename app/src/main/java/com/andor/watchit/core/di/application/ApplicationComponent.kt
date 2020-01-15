package com.andor.watchit.core.di.application

import com.andor.watchit.core.di.presentation.PresentationComponent
import com.andor.watchit.core.di.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}