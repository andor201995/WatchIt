package com.andor.watchit.screens.topratedmovielist.controller

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.andor.watchit.R
import com.andor.watchit.core.RxBaseObserver
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.topratedmovielist.model.Event
import com.andor.watchit.screens.topratedmovielist.model.TopRatedMovieListViewModel
import com.andor.watchit.screens.topratedmovielist.view.TopRatedMovieListViewMvc
import com.andor.watchit.usecase.common.model.GeneralMovie
import com.andor.watchit.usecase.common.model.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopRatedMovieListFragment : BaseFragment() {

    private lateinit var mViewModel: TopRatedMovieListViewModel

    private lateinit var mViewMvc: TopRatedMovieListViewMvc

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mScreenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewMvc = if (::mViewMvc.isInitialized) mViewMvc else mViewMvcFactory.getTopRatedMovieMvc(
            container
        )
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(TopRatedMovieListViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        bindToStreams()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.topratedmoviemenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mViewMvc.selectOptionItem(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun bindToStreams() {
        bindNetworkStateObserver()
        bindPagedListStateObserver()
        bindListEventObserver()
    }

    private fun bindListEventObserver() {
        mViewMvc.getEventStream()
            .subscribe(object : RxBaseObserver<Event>() {
                override fun onNext(t: Event) {
                    when (t) {
                        is Event.RetryListLoading -> {
                            mViewModel.retryLoadingList()
                        }
                        is Event.LoadMovie -> {
                            mScreenNavigator.navigateFromTopRatedScreenToMovieDetailScreen(t.generalMovie)
                        }
                        is Event.OpenSearchScreen -> {
                            mScreenNavigator.navigateToSearchScreen()
                        }
                    }
                }
            })
    }


    private fun bindPagedListStateObserver() {
        val pageListStateObserver =
            object : RxBaseObserver<PagedList<GeneralMovie>>() {
                override fun onNext(t: PagedList<GeneralMovie>) {
                    mViewMvc.updateList(t)
                }
            }
        mViewModel.topRatedMovieStream.subscribeOn(Schedulers.io())
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
                            mScreenNavigator.navigateToErrorScreen()
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
                            //do nothing
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
