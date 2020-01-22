package com.andor.watchit.screens.common

import android.app.Activity
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import com.andor.watchit.R
import com.andor.watchit.core.safeNavigation
import com.andor.watchit.screens.moviedetail.controller.MovieDetailFragmentDirections
import com.andor.watchit.screens.topratedmovielist.controller.TopRatedMovieListFragmentDirections
import com.andor.watchit.usecase.topratedmovie.TopRatedMovie

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

    fun navigateToMovieDetailScreen(topRatedMovie: TopRatedMovie, extra: Navigator.Extras) {
        val action =
            TopRatedMovieListFragmentDirections.actionTopRatedMovieListFragmentToMovieDetailFragment(
                topRatedMovie
            )
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(R.id.topRatedMovieListFragment, action)
    }

    fun navigateToPosterScreen(movieDetail: TopRatedMovie, extra: Navigator.Extras) {
        val action =
            MovieDetailFragmentDirections.actionMovieDetailFragmentToPosterFragment(movieDetail)
        Navigation.findNavController(activity, R.id.nav_host)
            .safeNavigation(R.id.movieDetailFragment, action)
    }


}