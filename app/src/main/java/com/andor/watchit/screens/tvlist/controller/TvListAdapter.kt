package com.andor.watchit.screens.tvlist.controller

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.mvc.ViewMvc
import com.andor.watchit.screens.tvlist.model.TvListEvent
import com.andor.watchit.screens.tvlist.view.TvListItemLoaderViewMvc
import com.andor.watchit.screens.tvlist.view.TvListItemViewMvc
import com.andor.watchit.usecase.common.model.TvUiModel
import io.reactivex.subjects.PublishSubject

class TvListAdapter(
    private val viewMvcFactory: ViewMvcFactory,
    private val tvListEventStream: PublishSubject<TvListEvent>
) : PagedListAdapter<TvUiModel, TvListAdapter.TvHolder>(
    tvDiffUtil
) {

    companion object {
        private val tvDiffUtil = object : DiffUtil.ItemCallback<TvUiModel>() {

            override fun areItemsTheSame(oldItem: TvUiModel, newItem: TvUiModel): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvUiModel, newItem: TvUiModel): Boolean {
                return oldItem == newItem
            }
        }

        private const val TYPE_PROGRESS = 0
        private const val TYPE_ITEM = 1
        private var listLoadingState: ListLoading =
            ListLoading.Loading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvHolder {
        val mViewMvc: ViewMvc = if (viewType == TYPE_PROGRESS) {
            viewMvcFactory.getTvListItemLoaderViewMvc(parent)
        } else {
            viewMvcFactory.getTvListItemViewMvc(parent)
        }
        return TvHolder(mViewMvc)
    }

    override fun onBindViewHolder(holder: TvHolder, position: Int) {

        when (val mViewMvc = holder.mViewMvc) {
            is TvListItemViewMvc -> {

                mViewMvc.getEventStream().subscribe(tvListEventStream)

                val uiModel = getItem(position)
                uiModel?.let {
                    mViewMvc.updateView(it)
                }
            }
            is TvListItemLoaderViewMvc -> {

                mViewMvc.getEventStream().subscribe(tvListEventStream)

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

    override fun onViewRecycled(holder: TvHolder) {
        super.onViewRecycled(holder)
        if (holder.mViewMvc is TvListItemViewMvc) {
            holder.mViewMvc.cleanUp()
        }
    }

    fun setListLoadingState(newListLoading: ListLoading) {
        if (listLoadingState != newListLoading) {
            listLoadingState = newListLoading
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        val itemCount = super.getItemCount()
        if (itemCount > 0) {
            tvListEventStream.onNext(TvListEvent.HideLoader)
        }
        return itemCount
    }

    inner class TvHolder(val mViewMvc: ViewMvc) :
        RecyclerView.ViewHolder(mViewMvc.getRootView())

    sealed class ListLoading {
        object Loading : ListLoading()
        object Error : ListLoading()
        object Completed : ListLoading()
    }
}
