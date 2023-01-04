package com.splanes.gifting.ui.feature.wishlists

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WishlistsRoute(viewModel: WishlistsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WishlistsRoute(
        uiState = uiState,
        onNewWishlistClick = viewModel::onNewWishlist,
        onNewWishlistDismiss = viewModel::onDismissNewWishlist,
        onCreateWishlist = viewModel::onCreateWishlist,
        onWishlistClick = viewModel::openWishlist,
        onDeleteWishlistClick = viewModel::openDeleteWishlistDialog,
        onNewWishlistItemClick = viewModel::onNewWishlistItem,
        onNewWishlistItemDismiss = viewModel::onDismissNewWishlistItem,
        onDeleteItemClick = viewModel::openDeleteWishlistItemDialog,
        onDeleteWishlistConfirmation = viewModel::onDeleteWishlist,
        onDeleteWishlistItemConfirmation = viewModel::onDeleteWishlistItem,
        onSelectWishlist = viewModel::onSelectWishlist,
        onUnselectWishlist = viewModel::onUnselectWishlist,
        onSelectWishlistItem = viewModel::onSelectWishlistItem,
        onUnselectWishlistItem = viewModel::onUnselectWishlistItem,
        onCloseWishlist = viewModel::onCloseWishlist,
        onCreateWishlistItem = viewModel::onCreateWishlistItem
    )
}

@Composable
fun WishlistsRoute(
    uiState: WishlistsUiState,
    onNewWishlistClick: () -> Unit,
    onNewWishlistDismiss: () -> Unit,
    onCreateWishlist: (NewWishlistRequest) -> Unit,
    onCreateWishlistItem: (WishlistItemFormResultData) -> Unit,
    onWishlistClick: (Wishlist) -> Unit,
    onDeleteWishlistClick: () -> Unit,
    onNewWishlistItemClick: () -> Unit,
    onNewWishlistItemDismiss: () -> Unit,
    onDeleteItemClick: () -> Unit,
    onDeleteWishlistConfirmation: (Wishlist) -> Unit,
    onDeleteWishlistItemConfirmation: (WishlistItem) -> Unit,
    onSelectWishlist: (Wishlist) -> Unit,
    onUnselectWishlist: (Wishlist) -> Unit,
    onSelectWishlistItem: (WishlistItem) -> Unit,
    onUnselectWishlistItem: (WishlistItem) -> Unit,
    onCloseWishlist: () -> Unit
) {
    Crossfade(uiState) { screenUiState ->
        when (screenUiState) {
            is WishlistsUiState.EmptyWishlists ->
                WishlistsEmptyScreen(
                    uiState = screenUiState,
                    onNewWishlistClick = onNewWishlistClick,
                    onNewWishlistDismiss = onNewWishlistDismiss,
                    onCreateWishlist = onCreateWishlist
                )

            is WishlistsUiState.Wishlists ->
                WishlistGridScreen(
                    uiState = screenUiState,
                    onNewWishlistClick = onNewWishlistClick,
                    onNewWishlistDismiss = onNewWishlistDismiss,
                    onCreateWishlist = onCreateWishlist,
                    onWishlistClick = onWishlistClick
                )

            is WishlistsUiState.WishlistsEditing -> {
            }

            is WishlistsUiState.EmptyWishlistOpen -> {
                EmptyWishlistOpenedScreen(
                    uiState = screenUiState,
                    onNewItemClick = onNewWishlistItemClick,
                    onNewItemDismiss = onNewWishlistItemDismiss,
                    onCreateItem = onCreateWishlistItem,
                    onCloseWishlist = onCloseWishlist
                )
            }

            is WishlistsUiState.WishlistOpen -> {
                /*WishlistOpenedScreen(
                    uiState = screenUiState,
                    onEditWishlist =,
                    onDeleteWishlist =,
                    onDeleteWishlistConfirm =,
                    onCreateItem =,
                    onDeleteItem =,
                    onEditItem =,
                    onNewItemClick = ,
                    onNewItemDismiss =
                )*/
            }

            is WishlistsUiState.WishlistOpenEditing -> {
            }
        }
    }
}
