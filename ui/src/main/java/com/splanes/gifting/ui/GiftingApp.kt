package com.splanes.gifting.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.splanes.gifting.ui.theme.GiftingTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GiftingApp() {
    GiftingTheme {
        val navController = rememberAnimatedNavController()
        val navActions = remember(navController) {
            GiftingNavigationActions(navController)
        }
        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: GiftingDestinations.Authentication

        GiftingNavGraph(
            navController = navController,
            navActions = navActions
        )
    }
}
