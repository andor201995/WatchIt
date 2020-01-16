package com.andor.watchit.screens.topratedmovielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.andor.watchit.R
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class TopRatedMovieListFragment : BaseFragment() {

    private lateinit var mViewModel: TopRatedMovieListViewModel

    private lateinit var mViewMvc: TopRatedMovieListViewMvc

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewMvc = mViewMvcFactory.getTopRatedMovieMvc(container)

        return inflater.inflate(R.layout.top_rated_movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(TopRatedMovieListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        val mScreenStateObserver = object : DisposableObserver<TopRatedMovieScreenState>() {

            override fun onNext(t: TopRatedMovieScreenState) {

                when (t.status) {
                    ScreenStatus.IDEAL -> {
                        mViewModel.fetchTopRatedMovieAndNotify()
                    }
                    ScreenStatus.FETCH_SUCCESS -> {
                        val listOfTopRatedMovie = t.listOfTopRatedMovie
                    }
                    ScreenStatus.FETCH_FAILED -> {

                    }
                    ScreenStatus.LOADING -> {

                    }
                }
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
            }

        }
        mViewModel.screenStateStream.subscribe(mScreenStateObserver)
        compositeDisposable.add(mScreenStateObserver)

        if (mViewModel.screenStateStream.value == null || mViewModel.screenStateStream.value!!.status == ScreenStatus.IDEAL) {
            mViewModel.fetchTopRatedMovieAndNotify()
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
