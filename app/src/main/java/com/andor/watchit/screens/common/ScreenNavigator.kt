package com.andor.watchit.screens.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.andor.watchit.R
import com.andor.watchit.core.safeNavigation
import com.andor.watchit.screens.listdetail.controller.ListDetailFragmentDirections
import com.andor.watchit.screens.listdetail.model.DetailUiModel
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragmentDirections
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragmentDirections
import com.andor.watchit.screens.tvlist.controller.TvListFragmentDirections
import com.andor.watchit.usecase.common.model.GeneralMovie
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
        generalMovie: GeneralMovie
    ) {
        val action =
            TopRatedMovieListFragmentDirections.actionTopRatedMovieListFragmentToListDetailFragment(
                generalMovie.toDetailModel()
            )
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.topRatedMovieListFragment, action)
    }

    fun navigateFromSearchScreenToMovieDetailScreen(
        fragment: Fragment,
        generalMovie: GeneralMovie
    ) {
        val action =
            SearchMovieFragmentDirections.actionSearchMovieFragmentToListDetailFragment(
                generalMovie.toDetailModel()
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
