package com.andor.watchit.screens.topratedmovielist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.BaseViewMvc
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller.TopRatedMovieListAdapter
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

class TopRatedMovieListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : BaseViewMvc(),
    TopRatedMovieListViewMvc {

    private var loader: ContentLoadingProgressBar
    private var recyclerView: RecyclerView
    private lateinit var adapter: TopRatedMovieListAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.top_rated_movie_list_fragment, parent, false))
        recyclerView = findViewById(R.id.top_rated_movie_list)
        loader = findViewById(R.id.loader)
        setUpRecyclerView(viewMvcFactory)
    }

    private fun setUpRecyclerView(viewMvcFactory: ViewMvcFactory) {

        adapter =
            TopRatedMovieListAdapter(
                viewMvcFactory
            )

        recyclerView.adapter = this.adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun updateList(listOfTopRatedMovie: List<TopRatedMovie>) {
        adapter.updateList(listOfTopRatedMovie)
    }

    override fun showLoader() {
        loader.show()
    }

    override fun hideLoader() {
        loader.hide()
    }
}