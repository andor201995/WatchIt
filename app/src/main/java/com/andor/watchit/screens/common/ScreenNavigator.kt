package com.andor.watchit.screens.common

import android.app.Activity
import androidx.navigation.Navigation
import com.andor.watchit.R
import com.andor.watchit.core.safeNavigation
import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragmentDirections
import com.andor.watchit.screens.searchmovie.controller.SearchMovieFragmentDirections
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragmentDirections
import com.andor.watchit.usecase.common.model.GeneralMovie

class ScreenNavigator(private val activity: Activity) {
    fun navigateToErrorScreen() {
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(
                R.id.topRatedMovieListFragment,
                R.id.action_topRatedMovieListFragment_to_networkErrorFragment
            )
    }

    fun navigateToTopRatedMovieListScreen() {
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(
                R.id.networkErrorFragment,
                R.id.action_networkErrorFragment_to_topRatedMovieListFragment
            )
    }

    fun navigateFromTopRatedScreenToMovieDetailScreen(generalMovie: GeneralMovie) {
        val action =
            TopRatedMovieListFragmentDirections.actionTopRatedMovieListFragmentToMovieDetailFragment(
                generalMovie
            )
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(R.id.topRatedMovieListFragment, action)
    }

    fun navigateFromSearchScreenToMovieDetailScreen(generalMovie: GeneralMovie) {
        val action =
            SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailFragment(
                generalMovie
            )
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(R.id.searchMovieFragment, action)
    }

    fun navigateToPosterScreen(movieDetail: GeneralMovie) {
        val action =
            MovieDetailFragmentDirections.actionMovieDetailFragmentToPosterFragment(movieDetail)
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(R.id.movieDetailFragment, action)
    }

    fun navigateToSearchScreen() {
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(
                R.id.topRatedMovieListFragment,
                R.id.action_topRatedMovieListFragment_to_searchMovieFragment
            )
    }

    fun navigateUp() {
        Navigation.findNavController(activity, R.id.nav_host).navigateUp()
    }

}