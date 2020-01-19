package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

class TopRatedMovieListAdapter(private val viewMvcFactory: ViewMvcFactory) :
    PagedListAdapter<TopRatedMovie, TopRatedMovieListAdapter.TopMovieHolder>(topRatedMovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieHolder {
        val mViewMvc = viewMvcFactory.getTopRatedMovieListItemViewMvc(parent)
        return TopMovieHolder(mViewMvc)
    }


    override fun onBindViewHolder(holder: TopMovieHolder, position: Int) {
        val topRatedMovie = getItem(position)
        topRatedMovie?.let {
            holder.mViewMvc.updateView(it)
        }
    }

    inner class TopMovieHolder(val mViewMvc: TopRatedMovieListItemViewMvc) :
        RecyclerView.ViewHolder(mViewMvc.getRootView())

    companion object {
        private val topRatedMovieDiffUtil = object : DiffUtil.ItemCallback<TopRatedMovie>() {

            override fun areItemsTheSame(oldItem: TopRatedMovie, newItem: TopRatedMovie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: TopRatedMovie,
                newItem: TopRatedMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
