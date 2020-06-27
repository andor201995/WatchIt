package com.andor.watchit.screens.listdetail.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andor.watchit.core.rx.RxBaseObserver
import com.andor.watchit.screens.common.ScreenNavigator
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.listdetail.model.DetailViewEvent
import com.andor.watchit.screens.listdetail.model.MovieDetailViewModel
import com.andor.watchit.screens.listdetail.view.MovieDetailViewMvc
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListDetailFragment : BaseFragment() {

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
            val detailUiModel = ListDetailFragmentArgs.fromBundle(arguments!!).detailModel
            mViewMvc.setDetails(detailUiModel)
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
        val eventObserver = object : RxBaseObserver<DetailViewEvent>() {
            override fun onNext(t: DetailViewEvent) {
                when (t) {
                    is DetailViewEvent.PosterClick -> {
                        mScreenNavigator.navigateToPosterScreen(
                            this@ListDetailFragment,
                            t.detailModel
                        )
                    }
                    is DetailViewEvent.PosterScrollToBack -> {
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
