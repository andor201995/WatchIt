package com.andor.watchit.screens.common.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.andor.watchit.core.di.utils.Injector
import java.util.UUID

abstract class BaseFragment : Fragment() {

    private var injected: Boolean = false

    companion object {
        const val INSTANCE_ID_KEY = "instance_Id_fragment"
    }

    private lateinit var instanceId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        instanceId =
            savedInstanceState?.getString(BaseActivity.INSTANCE_ID_KEY)?.let { return@let it }
                ?: UUID.randomUUID().toString()

        if (!injected) {
            Injector.inject(this)
            injected = true
        }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        Injector.clear(this)
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(INSTANCE_ID_KEY, instanceId)
    }

    internal fun getInstanceId() = instanceId
}
