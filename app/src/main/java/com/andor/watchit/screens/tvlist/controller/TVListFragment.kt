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
import com.andor.watchit.screens.tvlist.model.TvListEvent
import com.andor.watchit.screens.tvlist.view.TvListViewMvc
import com.andor.watchit.usecase.common.model.NetworkState
import com.andor.watchit.usecase.common.model.TvUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
            .subscribe(object : RxBaseObserver<TvListEvent>() {
                override fun onNext(t: TvListEvent) {
                    when (t) {
                        is TvListEvent.RetryListLoading -> {
                            mViewModel.retryLoadingList()
                        }
                        is TvListEvent.LoadTv -> {
                            mScreenNavigator.navigateFromTvListScreenToMovieDetailScreen(
                                this@TVListFragment,
                                t.tvUiModel
                            )
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

        val initialNetworkStateObserver =
            object : RxBaseObserver<NetworkState.Initial>() {
                override fun onNext(t: NetworkState.Initial) {
                    when (t) {
                        is NetworkState.Initial.Success -> {
                            mViewMvc.hideLoader()
                        }
                        is NetworkState.Initial.Error -> {
                            mViewMvc.hideLoader()
                            mScreenNavigator.navigateFromTopRatedScreenToErrorScreen(
                                this@TVListFragment
                            )
                        }
                        is NetworkState.Initial.Loading -> {
                            mViewMvc.showLoader()
                        }
                    }
                }
            }

        mViewModel.initialNetworkStateStream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(initialNetworkStateObserver)
        compositeDisposable.add(initialNetworkStateObserver)

        val nextNetworkStateObserver =
            object : RxBaseObserver<NetworkState.Next>() {
                override fun onNext(t: NetworkState.Next) {
                    when (t) {
                        is NetworkState.Next.Success -> {
                            // do nothing
                        }
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
                }
            }
        mViewModel.nextNetworkStateStream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(nextNetworkStateObserver)

        compositeDisposable.add(nextNetworkStateObserver)
    }
}
