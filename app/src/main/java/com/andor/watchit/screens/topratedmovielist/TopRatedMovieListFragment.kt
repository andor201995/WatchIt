package com.andor.watchit.screens.topratedmovielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.andor.watchit.R
import com.andor.watchit.screens.common.main.BaseFragment

class TopRatedMovieListFragment : BaseFragment() {

    private lateinit var viewModel: TopRatedMovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_rated_movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopRatedMovieListViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
