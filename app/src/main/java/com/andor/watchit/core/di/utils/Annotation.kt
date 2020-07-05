package com.andor.watchit.core.di.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import javax.inject.Singleton
import kotlin.reflect.KClass

// -- DAGGER SCOPE region begin --
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Singleton
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
// -- DAGGER SCOPE region end --

// -- KEY region begin --
@Target(AnnotationTarget.FUNCTION)
@MapKey
internal annotation class ActivityKey(val value: KClass<out Activity>)

@Target(AnnotationTarget.FUNCTION)
@MapKey
internal annotation class ScreenKey(val value: KClass<out Fragment>)

@Target(AnnotationTarget.FUNCTION)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)
// -- KEY region end --
