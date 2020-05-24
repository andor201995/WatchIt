package com.andor.watchit.core.di.application

import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.di.common.ApplicationScope
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        ActivityBindingModule::class,
        RepositoryModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {
    fun inject(mainApplication: MainApplication)
}