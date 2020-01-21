package com.andor.watchit.screens.moviedetail.controller

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.moviedetail.model.MovieDetailViewModel
import com.andor.watchit.screens.moviedetail.view.MovieDetailViewMvc
import javax.inject.Inject

class MovieDetailFragment : BaseFragment() {

    private lateinit var mViewMvc: MovieDetailViewMvc

    private lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presentationComponent.inject(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

}
