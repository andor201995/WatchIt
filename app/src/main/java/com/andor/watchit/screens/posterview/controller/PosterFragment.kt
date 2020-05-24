package com.andor.watchit.screens.posterview.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragmentArgs
import com.andor.watchit.screens.posterview.model.PosterViewModel
import com.andor.watchit.screens.posterview.view.PosterViewMvc
import javax.inject.Inject

class PosterFragment : BaseFragment() {

    private lateinit var mViewMvc: PosterViewMvc

    private lateinit var viewModel: PosterViewModel

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewMvc = mViewMvcFactory.getPosterViewMvc(container)
        if (arguments != null) {
            val movieDetail = MovieDetailFragmentArgs.fromBundle(arguments!!).movieDetail
            mViewMvc.setMoviePoster(movieDetail)
        }
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PosterViewModel::class.java)
    }

}
