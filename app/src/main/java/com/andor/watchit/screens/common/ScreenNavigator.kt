package com.andor.watchit.screens.common

import android.app.Activity
import androidx.navigation.Navigation
import com.andor.watchit.R

class ScreenNavigator(private val activity: Activity) {
    fun navigateToErrorScreen() {
        Navigation.findNavController(activity, R.id.nav_host)
            .navigate(R.id.action_topRatedMovieListFragment_to_networkErrorFragment)
    }

    fun navigateToTopRatedMovieListScreen() {
        Navigation.findNavController(activity, R.id.nav_host)
            .navigate(R.id.action_networkErrorFragment_to_topRatedMovieListFragment)
    }

}