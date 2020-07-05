package com.andor.watchit.screens.tvlist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.andor.watchit.core.rx.RxBaseObserver
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.common.helper.ScreenUtils
import com.andor.watchit.screens.tvlist.model.TvListEvent
import com.andor.watchit.screens.tvlist.view.TvListViewMvc
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.common.model.TvUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvListFragment : BaseFragment() {

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
        if (!::mViewMvc.isInitialized) mViewMvc = mViewMvcFactory.getTvListViewMvc(container)
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(TvListViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        mViewModel.retryLoadingList()
        bindToStreams()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    private fun bindToStreams() {
        bindNetworkStateObserver()
        bindPagedListStateObserver()
        bindListEventObserver()
    }

    private fun bindListEventObserver() {
        mViewMvc.getEventStream()
            .distinctUntilChanged()
            .subscribe(object : RxBaseObserver<TvListEvent>() {
                override fun onNext(t: TvListEvent) {
                    when (t) {
                        is TvListEvent.RetryListLoading -> {
                            mViewModel.retryLoadingList()
                        }
                        is TvListEvent.LoadTv -> {
                            mScreenNavigator.navigateFromTvListScreenToMovieDetailScreen(
                                this@TvListFragment,
                                t.tvUiModel
                            )
                        }
                        is TvListEvent.HideLoader -> {
                            mViewMvc.hideLoader()
                        }
                    }
                }
            })
    }

    private fun bindPagedListStateObserver() {
        val pageListStateObserver =
            object : RxBaseObserver<PagedList<TvUiModel>>() {
                override fun onNext(t: PagedList<TvUiModel>) {
                    mViewMvc.updateList(t)
                }
            }
        mViewModel.tvStream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(pageListStateObserver)

        compositeDisposable.add(pageListStateObserver)
    }

    private fun bindNetworkStateObserver() {
        ScreenUtils.bindNetworkStreamsAndNotify(
            {
                when (it) {
                    is NetworkState.Initial.Success -> {
                    }
                    is NetworkState.Initial.Error -> {
                        mScreenNavigator.navigateFromTopRatedScreenToErrorScreen(
                            this@TvListFragment
                        )
                    }
                    is NetworkState.Initial.Loading -> {
                        mViewMvc.showLoader()
                    }
                }
            },
            {
                when (it) {
                    is NetworkState.Next.Error -> {
                        mViewMvc.showListLoadingError()
                    }
                    is NetworkState.Next.Loading -> {
                        mViewMvc.showListLoading()
                    }
                    is NetworkState.Next.Completed -> {
                        mViewMvc.showListLoadingCompleted()
                    }
                }
            },
            mViewModel.initialNetworkStateStream,
            mViewModel.nextNetworkStateStream,
            compositeDisposable
        )
    }
}
