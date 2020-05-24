package com.andor.watchit.screens.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.andor.watchit.R
import com.andor.watchit.core.safeNavigation
import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragmentDirections
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragmentDirections
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragmentDirections
import com.andor.watchit.usecase.common.model.GeneralMovie

class ScreenNavigator() {
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
            TopRatedMovieListFragmentDirections.actionTopRatedMovieListFragmentToMovieDetailFragment(
                generalMovie
            )
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.topRatedMovieListFragment, action)
    }

    fun navigateFromSearchScreenToMovieDetailScreen(
        fragment: Fragment,
        generalMovie: GeneralMovie
    ) {
        val action =
            SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailFragment(
                generalMovie
            )
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.searchMovieFragment, action)
    }

    fun navigateToPosterScreen(fragment: Fragment, movieDetail: GeneralMovie) {
        val action =
            MovieDetailFragmentDirections.actionMovieDetailFragmentToPosterFragment(movieDetail)
        NavHostFragment.findNavController(fragment)
            .safeNavigation(R.id.movieDetailFragment, action)
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

}