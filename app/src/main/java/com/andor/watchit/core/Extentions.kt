package com.andor.watchit.core

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigation(currentDestinationResourceId: Int, actionId: Int) {
    if (currentDestination != null && currentDestination!!.id == currentDestinationResourceId) {
        navigate(actionId)
    }
}

fun NavController.safeNavigation(currentDestinationResourceId: Int, action: NavDirections) {
    if (currentDestination != null && currentDestination!!.id == currentDestinationResourceId) {
        navigate(action)
    }
}