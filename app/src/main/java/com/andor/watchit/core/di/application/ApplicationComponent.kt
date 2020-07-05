package com.andor.watchit.core.di.application

import android.content.Context
import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.di.utils.ApplicationScope
import dagger.BindsInstance
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

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}
