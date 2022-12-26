package com.splanes.gifting.ui.feature.wishlists

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WishlistsRoute(viewModel: WishlistsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WishlistsRoute(
        uiState = uiState,
        onNewWishlistClick = viewModel::onNewWishlist,
        onNewWishlistDismiss = viewModel::onDismissNewWishlist,
        onCreateWishlist = viewModel::onCreateWishlist,
    )
}

@Composable
fun WishlistsRoute(
    uiState: WishlistsUiState,
    onNewWishlistClick: () -> Unit,
    onNewWishlistDismiss: () -> Unit,
    onCreateWishlist: (NewWishlistRequest) -> Unit
) {
    Crossfade(uiState) { screenUiState ->
        when (screenUiState) {
            is WishlistsUiState.Empty ->
                WishlistsEmptyScreen(
                    uiState = screenUiState,
                    onNewWishlistClick = onNewWishlistClick,
                    onNewWishlistDismiss = onNewWishlistDismiss,
                    onCreateWishlist = onCreateWishlist
                )

            is WishlistsUiState.WishlistOpened -> TODO()
            is WishlistsUiState.Wishlists -> TODO()
        }
    }
}
