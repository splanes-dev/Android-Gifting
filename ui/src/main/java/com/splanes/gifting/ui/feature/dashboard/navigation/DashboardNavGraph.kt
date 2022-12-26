package com.splanes.gifting.ui.feature.dashboard.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.SentimentSatisfied
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.wishlists.WishlistsRoute
import com.splanes.gifting.ui.feature.wishlists.WishlistsViewModel

@Composable
fun DashboardNavGraph(
    navController: NavHostController,
    navActions: DashboardNavigationActions,
    current: NavBackStackEntry?,
    modifier: Modifier = Modifier,
    startDestination: String = DashboardDestinations.Home
) {
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(
            modifier = modifier.weight(1f),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(DashboardDestinations.Home) {
            }
            composable(DashboardDestinations.Wishlists) {
                val viewModel: WishlistsViewModel = hiltViewModel()
                WishlistsRoute(viewModel)
            }
            composable(DashboardDestinations.GroupLists) {
            }
            composable(DashboardDestinations.Profile) {
            }
            composable(DashboardDestinations.InvisibleFriend) {
            }
        }
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = colorOf { primaryContainer },
            contentColor = colorOf { onPrimaryContainer }
        ) {
            DashboardDestinations.all.forEach { destination ->

                val label = when (destination) {
                    DashboardDestinations.Home -> R.string.home
                    DashboardDestinations.Wishlists -> R.string.wishlists
                    DashboardDestinations.GroupLists -> R.string.group_lists
                    DashboardDestinations.InvisibleFriend -> R.string.invisible_friend
                    DashboardDestinations.Profile -> R.string.profile
                    else -> error("Destination not registered")
                }.let { id -> stringResource(id) }

                BottomNavigationItem(
                    selected = current.isSelected(destination),
                    onClick = when (destination) {
                        DashboardDestinations.Home -> navActions.navToHome
                        DashboardDestinations.Wishlists -> navActions.navToWishlists
                        DashboardDestinations.GroupLists -> navActions.navToGroupLists
                        DashboardDestinations.InvisibleFriend -> navActions.navToInvisibleFriend
                        DashboardDestinations.Profile -> navActions.navToProfile
                        else -> error("Destination not registered")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.run {
                                when (destination) {
                                    DashboardDestinations.Home -> Home
                                    DashboardDestinations.Wishlists -> Favorite
                                    DashboardDestinations.GroupLists -> Groups
                                    DashboardDestinations.InvisibleFriend -> SentimentSatisfied
                                    DashboardDestinations.Profile -> Person
                                    else -> error("Destination not registered")
                                }
                            },
                            contentDescription = label,
                            tint = colorOf {
                                if (current.isSelected(destination)) {
                                    onPrimaryContainer
                                } else {
                                    onPrimaryContainer.withAlpha(.3)
                                }
                            }
                        )
                    },
                    label = {
                        Text(
                            text = label,
                            style = textStyleOf { labelSmall }.copy(fontWeight = FontWeight.Medium),
                            color = colorOf { onPrimaryContainer },
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = colorOf { onPrimary },
                    unselectedContentColor = colorOf { onPrimary.withAlpha(.3) }
                )
            }
        }
    }
}

private fun NavBackStackEntry?.isSelected(route: String) =
    this?.destination?.route == route
