package com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.controller

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.screens.topratedmovielist.model.MovieListEvent
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemLoaderViewMvc
import com.andor.watchit.screens.topratedmovielist.view.topratedlistitem.view.TopRatedMovieListItemViewMvc
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.PublishSubject

class TopRatedMovieListAdapter(
    private val viewMvcFactory: ViewMvcFactory,
    private val movieListEventStream: PublishSubject<MovieListEvent>
) : PagedListAdapter<GeneralMovie, TopRatedMovieListAdapter.TopMovieHolder>(
    topRatedMovieDiffUtil
) {
    companion object {
        private val topRatedMovieDiffUtil = object : DiffUtil.ItemCallback<GeneralMovie>() {

            override fun areItemsTheSame(oldItem: GeneralMovie, newItem: GeneralMovie): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: GeneralMovie,
                newItem: GeneralMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val TYPE_PROGRESS = 0
    private val TYPE_ITEM = 1
    private var listLoadingState: ListLoading = ListLoading.Loading

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieHolder {
        val mViewMvc: ViewMvc = if (viewType == TYPE_PROGRESS) {
            viewMvcFactory.getTopRatedMovieListItemLoaderViewMvc(parent)
        } else {
            viewMvcFactory.getTopRatedMovieListItemViewMvc(parent)
        }
        return TopMovieHolder(mViewMvc)
    }

    override fun onBindViewHolder(holder: TopMovieHolder, position: Int) {

        when (val mViewMvc = holder.mViewMvc) {
            is TopRatedMovieListItemViewMvc -> {

                mViewMvc.getEventStream().subscribe(movieListEventStream)

                val topRatedMovie = getItem(position)
                topRatedMovie?.let {
                    mViewMvc.updateView(it)
                }
            }
            is TopRatedMovieListItemLoaderViewMvc -> {

                mViewMvc.getEventStream().subscribe(movieListEventStream)

                when (listLoadingState) {
                    is ListLoading.Loading -> {
                        mViewMvc.showLoader()
                    }
                    is ListLoading.Error -> {
                        mViewMvc.showError()
                    }
                    is ListLoading.Completed -> {
                        mViewMvc.hideRow()
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (listLoadingState !is ListLoading.Completed && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    override fun onViewRecycled(holder: TopMovieHolder) {
        super.onViewRecycled(holder)
        if (holder.mViewMvc is TopRatedMovieListItemViewMvc) {
            holder.mViewMvc.cleanUp()
        }
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        if (itemCount > 0) movieListEventStream.onNext(MovieListEvent.HideLoader)
        return itemCount
    }

    fun setListLoadingState(newListLoading: ListLoading) {
        if (listLoadingState != newListLoading) {
            listLoadingState = newListLoading
            notifyDataSetChanged()
        }
    }

    inner class TopMovieHolder(val mViewMvc: ViewMvc) :
        RecyclerView.ViewHolder(mViewMvc.getRootView())

    sealed class ListLoading {
        object Loading : ListLoading()
        object Error : ListLoading()
        object Completed : ListLoading()
    }
}
