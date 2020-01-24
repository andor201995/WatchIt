package com.andor.watchit.screens.searchmovie.controller

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import com.andor.watchit.R
import com.andor.watchit.core.RxBaseObserver
import com.andor.watchit.screens.common.ViewModelFactory
import com.andor.watchit.screens.common.ViewMvcFactory
import com.andor.watchit.screens.common.controller.BaseFragment
import com.andor.watchit.screens.searchmovie.model.Event
import com.andor.watchit.screens.searchmovie.model.SearchMovieViewModel
import com.andor.watchit.screens.searchmovie.view.SearchMovieViewMvc
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class SearchMovieFragment : BaseFragment() {

    private lateinit var mViewMvc: SearchMovieViewMvc
    private lateinit var mViewModel: SearchMovieViewModel
    private val disposable = CompositeDisposable()

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewMvc = mViewMvcFactory.getSearchViewMvc(container)
        return mViewMvc.getRootView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel =
            ViewModelProvider(this, mViewModelFactory).get(SearchMovieViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        bindToViewEventStream()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.searchmoviemenu, menu)
        mViewMvc.setSearchBar(menu, requireActivity())
    }

    private fun bindToViewEventStream() {
        val observer = object : RxBaseObserver<Event>() {
            override fun onNext(t: Event) {
                if (t is Event.FindMovie) {
                    mViewModel.findMovie(t.query)
                }
            }
        }
        disposable.add(observer)
        mViewMvc.getEventStream().subscribe(observer)
    }
}
