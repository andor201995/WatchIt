package com.andor.watchit.screens.searchmovie.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.andor.watchit.R
import com.andor.watchit.core.rx.RxBaseObserver
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.searchmovie.model.SearchMovieViewModel
import com.andor.watchit.screens.searchmovie.model.SearchViewEvent
import com.andor.watchit.screens.searchmovie.view.SearchMovieViewMvc
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.andor.watchit.usecase.common.model.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchMovieFragment : BaseFragment() {

    private val searchQueryTag = "searchQuery"
    private var mSearchQuery: String = ""
    private lateinit var mViewMvc: SearchMovieViewMvc
    private lateinit var mViewModel: SearchMovieViewModel
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    lateinit var mScreenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        savedInstanceState?.let {
            mSearchQuery = savedInstanceState.getString(searchQueryTag, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!(::mViewMvc.isInitialized)) {
            mViewMvc = mViewMvcFactory.getSearchViewMvc(container)
        }
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(SearchMovieViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        mViewModel.retryLoadingList()
        bindStreams()
    }

    private fun bindStreams() {
        bindToViewEventStream()
        bindPagedListStateObserver()
        bindNetworkStateObserver()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.searchmoviemenu, menu)
        mViewMvc.setSearchBar(menu, requireActivity(), mSearchQuery)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(searchQueryTag, mSearchQuery)
        super.onSaveInstanceState(outState)
    }

    private fun bindToViewEventStream() {
        val observer = object : RxBaseObserver<SearchViewEvent>() {
            override fun onNext(t: SearchViewEvent) {
                when (t) {
                    is SearchViewEvent.FindMovie -> {
                        mViewModel.findMovie(t.query)
                        mSearchQuery = t.query
                    }
                    is SearchViewEvent.SearchCollapse -> {
                        mScreenNavigator.navigateUp(this@SearchMovieFragment)
                    }
                    is SearchViewEvent.OpenMovie -> {
                        mScreenNavigator.navigateFromSearchScreenToMovieDetailScreen(
                            this@SearchMovieFragment,
                            t.movieUiModel
                        )
                    }
                    is SearchViewEvent.HideLoader -> {
                        mViewMvc.hideLoader()
                    }
                }
            }
        }
        compositeDisposable.add(observer)
        mViewMvc.getEventStream()
            .distinctUntilChanged()
            .subscribe(observer)
    }

    private fun bindPagedListStateObserver() {
        val pageListStateObserver =
            object : RxBaseObserver<PagedList<MovieUiModel>>() {
                override fun onNext(t: PagedList<MovieUiModel>) {
                    mViewMvc.updateList(t)
                }
            }
        mViewModel.movieUiModelStream.subscribeOn(Schedulers.io())
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
                            mViewMvc.hidePlaceHolder()
                            mViewMvc.hideLoader()
                            if (t.totalResult == 0) {
                                mViewMvc.showEmptyListPlaceholder()
                            }
                        }
                        is NetworkState.Initial.Error -> {
                            mViewMvc.hidePlaceHolder()
                            mViewMvc.hideLoader()
                            mViewMvc.hideEmptyListPlaceholder()
                            mScreenNavigator.navigateFromSearchScreenToErrorScreen(
                                this@SearchMovieFragment
                            )
                        }
                        is NetworkState.Initial.Loading -> {
                            mViewMvc.showLoader()
                            mViewMvc.hidePlaceHolder()
                            mViewMvc.hideEmptyListPlaceholder()
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
//                            mViewMvc.showListLoadingError()
                        }
                        is NetworkState.Next.Loading -> {
//                            mViewMvc.showListLoading()
                        }
                        is NetworkState.Next.Completed -> {
//                            mViewMvc.showListLoadingCompleted()
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
