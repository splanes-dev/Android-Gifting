package com.splanes.gifting.ui.feature.dashboard.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object DashboardDestinations {
    val all: List<String>
        get() = listOf(
            InvisibleFriend,
            GroupLists,
            Home,
            Wishlists,
            Profile
        )
    const val Profile = "dashboard/profile"
    const val Wishlists = "dashboard/wishlists"
    const val GroupLists = "dashboard/group-lists"
    const val InvisibleFriend = "dashboard/invisible-friend"
    const val Home = "dashboard/home"
}

class DashboardNavigationActions(private val navController: NavHostController) {
    val navToProfile: () -> Unit = {
        navController.navigate(DashboardDestinations.Profile) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToWishlists: () -> Unit = {
        navController.navigate(DashboardDestinations.Wishlists) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToGroupLists: () -> Unit = {
        navController.navigate(DashboardDestinations.GroupLists) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToInvisibleFriend: () -> Unit = {
        navController.navigate(DashboardDestinations.InvisibleFriend) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
    val navToHome: () -> Unit = {
        navController.navigate(DashboardDestinations.Home) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            restoreState = true
        }
    }
}
