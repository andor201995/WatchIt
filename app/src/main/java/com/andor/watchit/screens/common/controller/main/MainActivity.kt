package com.andor.watchit.screens.common.controller.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.andor.watchit.R
import com.andor.watchit.core.inVisible
import com.andor.watchit.core.visible
import com.andor.watchit.screens.common.controller.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setupNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(findNavController(R.id.nav_host)) ||
                super.onOptionsItemSelected(item)

    override fun getLayoutRes() = R.layout.activity_main

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(
        this,
        R.id.nav_host
    ).navigateUp(appBarConfiguration)

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun hideNavViews() {
        btm_nav_bar.inVisible()
        searchFAB.inVisible()
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun showNavViews() {
        btm_nav_bar.visible()
        searchFAB.visible()
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun setupNavigation() {
        val navController = NavHostFragment.findNavController(nav_host)

        appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.topRatedMovieListFragment,
                R.id.tvListFragment,
                R.id.settingFragment
            )
            .setDrawerLayout(drawer_layout)
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navigationView, navController)

        setupBottomNavViewAndFAB(navController)
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
                R.id.topRatedMovieListFragment -> showNavViews()
                R.id.tvListFragment -> showNavViews()
                else -> hideNavViews()
            }
        }
    }
}
