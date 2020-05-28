package com.andor.watchit.screens.common.controller.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.andor.watchit.R
import com.andor.watchit.screens.common.controller.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationUI.setupActionBarWithNavController(
            this,
            NavHostFragment.findNavController(nav_host)
        )
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(
        this,
        R.id.nav_host
    ).navigateUp()
}
