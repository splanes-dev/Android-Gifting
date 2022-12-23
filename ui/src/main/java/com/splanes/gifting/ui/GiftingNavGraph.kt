package com.splanes.gifting.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.splanes.gifting.ui.feature.authentication.AuthRoute
import com.splanes.gifting.ui.feature.authentication.AuthViewModel
import com.splanes.gifting.ui.feature.dashboard.DashboardRoute

@Composable
fun GiftingNavGraph(
    navController: NavHostController,
    navActions: GiftingNavigationActions,
    modifier: Modifier = Modifier,
    startDestination: String = GiftingDestinations.Authentication
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(GiftingDestinations.Authentication) {
            val viewModel: AuthViewModel = hiltViewModel()
            AuthRoute(
                viewModel = viewModel,
                onNavToDashboard = navActions.navToDashboard
            )
        }
        composable(GiftingDestinations.Dashboard) {
            DashboardRoute()
        }
    }
}
