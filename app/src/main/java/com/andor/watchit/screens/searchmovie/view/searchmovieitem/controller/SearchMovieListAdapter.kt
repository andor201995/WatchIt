package com.andor.watchit.screens.searchmovie.view.searchmovieitem.controller

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.screens.searchmovie.model.SearchViewEvent
import com.andor.watchit.screens.searchmovie.view.searchmovieitem.view.SearchMovieItemViewMvc
import com.andor.watchit.usecase.common.model.GeneralMovie
import io.reactivex.subjects.PublishSubject

class SearchMovieListAdapter(
    private val viewMvcFactory: ViewMvcFactory,
    private val searchViewEventStream: PublishSubject<SearchViewEvent>
) : PagedListAdapter<GeneralMovie, SearchMovieListAdapter.SearchMovieHolder>(
    searchedMovieDiffUtil
) {
    companion object {
        private val searchedMovieDiffUtil = object : DiffUtil.ItemCallback<GeneralMovie>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieHolder {
        val mViewMvc = viewMvcFactory.getSearchMovieItemViewMvc(parent)
        return SearchMovieHolder(mViewMvc)
    }

    override fun onBindViewHolder(holder: SearchMovieHolder, position: Int) {
        val viewMvc = holder.viewMvc
        val movie = getItem(position)

        if (viewMvc is SearchMovieItemViewMvc && movie != null) {
            viewMvc.bindItem(movie)
            viewMvc.getEventStream().subscribe(searchViewEventStream)
        }
    }

    override fun onViewRecycled(holder: SearchMovieHolder) {
        super.onViewRecycled(holder)
        if (holder.viewMvc is SearchMovieItemViewMvc) {
            holder.viewMvc.cleanUp()
        }
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        if (itemCount > 0) {
            searchViewEventStream.onNext(SearchViewEvent.HideLoader)
        }
        return itemCount
    }

    inner class SearchMovieHolder(val viewMvc: ViewMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView())
}
