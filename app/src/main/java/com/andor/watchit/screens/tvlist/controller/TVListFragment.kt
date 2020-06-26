package com.andor.watchit.screens.tvlist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.tvlist.model.TvListViewModel
import com.andor.watchit.screens.tvlist.view.TvListViewMvc
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TVListFragment : BaseFragment() {

    private lateinit var mViewMvc: TvListViewMvc
    private lateinit var mViewModel: TvListViewModel
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mScreenNavigator: ScreenNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewMvc =
            if (::mViewMvc.isInitialized) mViewMvc else mViewMvcFactory.getTvListViewMvc(container)

        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(TvListViewModel::class.java)
    }
}
