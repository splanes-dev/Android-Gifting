package com.splanes.gifting.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.splanes.gifting.ui.feature.authentication.AuthRoute
import com.splanes.gifting.ui.feature.authentication.AuthViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GiftingNavGraph(
    navController: NavHostController,
    navActions: GiftingNavigationActions,
    modifier: Modifier = Modifier,
    startDestination: String = GiftingDestinations.Authentication
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(GiftingDestinations.Authentication) {
            val viewModel: AuthViewModel = hiltViewModel()
            AuthRoute(
                viewModel = viewModel,
                onNavToDashboard = navActions.navToDashboard
            )
        }
    }
}
