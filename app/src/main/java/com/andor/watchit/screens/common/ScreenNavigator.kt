package com.andor.watchit.screens.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.andor.watchit.R
import com.andor.watchit.core.extensions.safeNavigation
import com.andor.watchit.screens.listdetail.controller.ListDetailFragmentDirections
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.movielist.controller.TopRatedMovieListFragmentDirections
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragmentDirections
import com.andor.watchit.screens.tvlist.controller.TvListFragmentDirections
import com.andor.watchit.usecase.common.model.MovieUiModel
import com.andor.watchit.usecase.common.model.TvUiModel
import com.andor.watchit.usecase.common.model.toDetailModel

class ScreenNavigator {
    fun navigateFromTopRatedScreenToErrorScreen(fragment: Fragment) {
        NavHostFragment.findNavController(fragment)
            .safeNavigation(
                R.id.topRatedMovieListFragment,
                R.id.action_topRatedMovieListFragment_to_networkErrorFragment
            )
    }

    fun navigateFromSearchScreenToErrorScreen(fragment: Fragment) {
        NavHostFragment.findNavController(fragment)
            .safeNavigation(
                R.id.searchMovieFragment,
                R.id.action_searchMovieFragment_to_networkErrorFragment
            )
    }

    fun navigateFromTopRatedScreenToMovieDetailScreen(
        fragment: Fragment,
        movieUiModel: MovieUiModel
    ) {
        val action =
            TopRatedMovieListFragmentDirections.actionTopRatedMovieListFragmentToListDetailFragment(
                movieUiModel.toDetailModel()
            )
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.topRatedMovieListFragment, action)
    }

    fun navigateFromSearchScreenToMovieDetailScreen(
        fragment: Fragment,
        movieUiModel: MovieUiModel
    ) {
        val action =
            SearchMovieFragmentDirections.actionSearchMovieFragmentToListDetailFragment(
                movieUiModel.toDetailModel()
            )
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.searchMovieFragment, action)
    }

    fun navigateToPosterScreen(fragment: Fragment, detailModel: DetailUiModel) {
        val action =
            ListDetailFragmentDirections.actionListDetailFragmentToPosterFragment(detailModel)
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.listDetailFragment, action)
    }

    fun navigateToSearchScreen(fragment: Fragment) {
        NavHostFragment.findNavController(fragment)
            .safeNavigation(
                R.id.topRatedMovieListFragment,
                R.id.action_topRatedMovieListFragment_to_searchMovieFragment
            )
    }

    fun navigateUp(fragment: Fragment) {
        NavHostFragment.findNavController(fragment).navigateUp()
    }

    fun navigateFromTvListScreenToMovieDetailScreen(
        fragment: Fragment,
        tvUiModel: TvUiModel
    ) {
        val action =
            TvListFragmentDirections.actionTVListFragmentToListDetailFragment(
                tvUiModel.toDetailModel()
            )
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.tvListFragment, action)
    }
}
