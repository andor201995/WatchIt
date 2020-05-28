package com.andor.watchit.screens.moviedetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andor.watchit.core.rx.RxBaseObserver
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.moviedetail.model.Event
import com.andor.watchit.screens.moviedetail.model.MovieDetailViewModel
import com.andor.watchit.screens.moviedetail.view.MovieDetailViewMvc
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieDetailFragment : BaseFragment() {

    private lateinit var mViewMvc: MovieDetailViewMvc

    private lateinit var viewModel: MovieDetailViewModel

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mScreenNavigator: ScreenNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewMvc = mViewMvcFactory.getMovieDetailViewMvc(container)
        if (arguments != null) {
            val movieDetail = MovieDetailFragmentArgs.fromBundle(arguments!!).movieDetail
            mViewMvc.setMovieDetails(movieDetail)
        }
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        bindEventStream()
    }

    private fun bindEventStream() {
        val eventObserver = object : RxBaseObserver<Event>() {
            override fun onNext(t: Event) {
                when (t) {
                    is Event.PosterClick -> {
                        mScreenNavigator.navigateToPosterScreen(
                            this@MovieDetailFragment,
                            t.movieDetail
                        )
                    }
                    is Event.PosterScrollToBack -> {
                    }
                }
            }
        }
        compositeDisposable.add(eventObserver)
        mViewMvc.getEventStream().subscribe(eventObserver)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}
