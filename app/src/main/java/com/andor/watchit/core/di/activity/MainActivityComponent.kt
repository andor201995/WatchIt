package com.andor.watchit.core.di.activity

import com.andor.watchit.core.di.common.ActivityScope
import com.andor.watchit.screens.common.controller.main.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(
    modules = [
        ScreenBindingModule::class,
        MainActivityModule::class
    ]
)
@ActivityScope
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MainActivity>
}
