package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvc
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

class TopRatedMovieListAdapter(private val viewMvcFactory: ViewMvcFactory) :
    RecyclerView.Adapter<TopRatedMovieListAdapter.TopMovieHolder>() {


    private val listOfTopRatedMovie: ArrayList<TopRatedMovie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieHolder {
        val mViewMvc = viewMvcFactory.getTopRatedMovieListItemViewMvc(parent)
        return TopMovieHolder(mViewMvc)
    }

    override fun getItemCount(): Int {
        return listOfTopRatedMovie.size
    }

    override fun onBindViewHolder(holder: TopMovieHolder, position: Int) {
        holder.mViewMvc.updateView(listOfTopRatedMovie[position])
    }

    fun updateList(newListOfTopRatedMovie: List<TopRatedMovie>) {
        this.listOfTopRatedMovie.clear()
        this.listOfTopRatedMovie.addAll(newListOfTopRatedMovie)
        notifyDataSetChanged()
    }

    inner class TopMovieHolder(val mViewMvc: TopRatedMovieListItemViewMvc) :
        RecyclerView.ViewHolder(mViewMvc.getRootView())
}
