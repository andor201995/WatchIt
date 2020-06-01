package com.andor.watchit.screens.common.controller.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.andor.watchit.R
import com.andor.watchit.core.inVisible
import com.andor.watchit.core.visible
import com.andor.watchit.screens.common.controller.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = NavHostFragment.findNavController(nav_host)
        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.topRatedMovieListFragment,
                R.id.tvListFragment
            )
            .build()

        setupBottomNavViewAndFAB(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavViewAndFAB(navController: NavController) {
        btm_nav_bar.setupWithNavController(navController)
        btm_nav_bar.isItemHorizontalTranslationEnabled = true
        btm_nav_bar.setOnNavigationItemReselectedListener {
            // do nothing when reselected
        }

        searchFAB.setOnClickListener {
            navController.navigate(R.id.searchMovieFragment)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.topRatedMovieListFragment -> showBottomNav()
                R.id.tvListFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        btm_nav_bar.inVisible()
        searchFAB.inVisible()
    }

    private fun showBottomNav() {
        btm_nav_bar.visible()
        searchFAB.visible()
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(
        this,
        R.id.nav_host
    ).navigateUp()
}
