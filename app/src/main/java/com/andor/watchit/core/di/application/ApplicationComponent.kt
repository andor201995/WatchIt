package com.andor.watchit.core.di.application

import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.di.common.ApplicationScope
import com.andor.watchit.core.di.screen.ScreenComponent
import com.andor.watchit.core.di.screen.ScreenModule
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        ActivityBindingModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {
    fun getPresentationComponent(screenModule: ScreenModule): ScreenComponent
    fun inject(mainApplication: MainApplication)
}