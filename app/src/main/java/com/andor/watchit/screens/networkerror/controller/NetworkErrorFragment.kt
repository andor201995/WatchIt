package com.andor.watchit.screens.networkerror.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.networkerror.model.NetworkErrorViewModel
import com.andor.watchit.screens.networkerror.view.NetworkErrorViewMvc
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class NetworkErrorFragment : BaseFragment() {

    private lateinit var mViewMvc: NetworkErrorViewMvc
    private lateinit var viewModel: NetworkErrorViewModel
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory
    @Inject
    lateinit var mScreenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewMvc = mViewMvcFactory.getNetworkErrorViewMvc(container)
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NetworkErrorViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        bindViewEvent()
    }

    private fun bindViewEvent() {
        val eventObserve = object : DisposableObserver<NetworkErrorViewMvc.Event>() {
            override fun onComplete() {
            }

            override fun onNext(t: NetworkErrorViewMvc.Event) {
                when (t) {
                    NetworkErrorViewMvc.Event.SwipeRefresh -> {
                        mScreenNavigator.navigateUp()
                    }
                }
            }

            override fun onError(e: Throwable) {
            }

        }
        compositeDisposable.add(eventObserve)
        mViewMvc.getEventStream().subscribe(eventObserve)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
