package com.andor.watchit.screens.topratedmovielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.R
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.BaseViewMvc
import com.andor.watchit.screens.topratedmovielist.topratedlistitem.TopRatedMovieListAdapter
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

class TopRatedMovieListViewMvcImpl(
    parent: ViewGroup?,
    layoutInflater: LayoutInflater,
    viewMvcFactory: ViewMvcFactory
) : BaseViewMvc(), TopRatedMovieListViewMvc {

    private var recyclerView: RecyclerView
    private lateinit var adapter: TopRatedMovieListAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.top_rated_movie_list_fragment, parent, false))
        recyclerView = findViewById(R.id.top_rated_movie_list)
        setUpRecyclerView(viewMvcFactory)
    }

    private fun setUpRecyclerView(viewMvcFactory: ViewMvcFactory) {

        adapter = TopRatedMovieListAdapter(viewMvcFactory)

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
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}