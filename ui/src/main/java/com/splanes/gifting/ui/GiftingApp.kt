package com.splanes.gifting.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun GiftingApp() {
    GiftingTheme {
        val navController = rememberNavController()
        val navActions = remember(navController) {
            GiftingNavigationActions(navController)
        }
        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: GiftingDestinations.Authentication

        GiftingNavGraph(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            navActions = navActions
        )
    }
}
