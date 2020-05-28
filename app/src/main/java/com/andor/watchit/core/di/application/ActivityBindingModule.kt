package com.andor.watchit.core.di.application

import android.app.Activity
import com.andor.watchit.core.di.activity.MainActivityComponent
import com.andor.watchit.core.di.common.ActivityKey
import com.andor.watchit.screens.common.controller.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        MainActivityComponent::class
    ]
)
interface ActivityBindingModule {

    /*
    * https://stackoverflow.com/a/60529418/7972699
    * @ActivityKey is removed and @ClassKey is substitute to bind
    * */
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    fun bindMainActivityInjector(factory: MainActivityComponent.Factory): AndroidInjector.Factory<out Activity>
}
