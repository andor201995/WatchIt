package com.andor.watchit.screens.common.controller

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.andor.watchit.core.di.common.Injector
import com.andor.watchit.core.di.screen.FragmentInjector
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

//    @Inject
//    lateinit var fragmentInjector: FragmentInjector

    companion object {
        const val INSTANCE_ID_KEY = "instance_Id_activity"
    }

    private lateinit var instanceId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        instanceId = savedInstanceState?.getString(INSTANCE_ID_KEY)?.let { return@let it }
            ?: UUID.randomUUID().toString()

        Injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INSTANCE_ID_KEY, instanceId)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            Injector.clear(this)
        }
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    fun getFragmentInjector(): FragmentInjector {
        TODO("In fragment iteration")
    }

    internal fun getInstanceId() = instanceId
}