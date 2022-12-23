package com.splanes.gifting.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object GiftingDestinations {
    const val Authentication = "auth"
    const val Dashboard = "dashboard"
}

class GiftingNavigationActions(private val navController: NavHostController) {
    val navToAuth: () -> Unit = {
        navController.navigate(GiftingDestinations.Authentication) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
    val navToDashboard: () -> Unit = {
        navController.navigate(GiftingDestinations.Dashboard) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
