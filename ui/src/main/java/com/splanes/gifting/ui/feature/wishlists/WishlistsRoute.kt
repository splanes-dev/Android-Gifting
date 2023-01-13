package com.splanes.gifting.ui.feature.wishlists

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.feature.wishlists.model.WishlistFormResultData
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun WishlistsRoute(viewModel: WishlistsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WishlistsRoute(
        uiState = uiState,
        onCreateWishlist = viewModel::onCreateWishlist,
        onEditWishlist = viewModel::onEditWishlist,
        onWishlistClick = viewModel::openWishlist,
        onWishlistItemClick = viewModel::openWishlistItem,
        onDeleteWishlistConfirmation = viewModel::onDeleteWishlists,
        onDeleteWishlistItemConfirmation = viewModel::onDeleteWishlistItem,
        onSelectWishlist = viewModel::onSelectWishlist,
        onUnselectWishlist = viewModel::onUnselectWishlist,
        onSelectWishlistItem = viewModel::onSelectWishlistItem,
        onUnselectWishlistItem = viewModel::onUnselectWishlistItem,
        onCloseWishlist = viewModel::onCloseWishlist,
        onCloseWishlistItem = viewModel::onCloseWishlistItem,
        onCreateWishlistItem = viewModel::onCreateWishlistItem,
        onUpdateWishlistItem = viewModel::onUpdateWishlistItem
    )
}

@Composable
fun WishlistsRoute(
    uiState: WishlistsUiState,
    onCreateWishlist: (WishlistFormResultData) -> Unit,
    onEditWishlist: (WishlistFormResultData) -> Unit,
    onCreateWishlistItem: (WishlistItemFormResultData) -> Unit,
    onUpdateWishlistItem: (item: WishlistItem, form: WishlistItemFormResultData) -> Unit,
    onWishlistClick: (Wishlist) -> Unit,
    onWishlistItemClick: (WishlistItem) -> Unit,
    onDeleteWishlistConfirmation: (List<Wishlist>) -> Unit,
    onDeleteWishlistItemConfirmation: (WishlistItem) -> Unit,
    onSelectWishlist: (Wishlist) -> Unit,
    onUnselectWishlist: (Wishlist) -> Unit,
    onSelectWishlistItem: (WishlistItem) -> Unit,
    onUnselectWishlistItem: (WishlistItem) -> Unit,
    onCloseWishlist: () -> Unit,
    onCloseWishlistItem: () -> Unit
) {
    Crossfade(uiState) { screenUiState ->
        when (screenUiState) {
            is WishlistsUiState.EmptyWishlists ->
                WishlistsEmptyScreen(
                    uiState = screenUiState,
                    onCreateWishlist = onCreateWishlist
                )

            is WishlistsUiState.Wishlists ->
                WishlistGridScreen(
                    uiState = screenUiState,
                    onCreateWishlist = onCreateWishlist,
                    onWishlistClick = onWishlistClick,
                    onWishlistLongClick = onSelectWishlist
                )

            is WishlistsUiState.WishlistsEditing ->
                WishlistGridEditScreen(
                    uiState = screenUiState,
                    onSelectWishlist = onSelectWishlist,
                    onUnselectWishlist = onUnselectWishlist,
                    onDeleteWishlist = onDeleteWishlistConfirmation,
                    onEditWishlist = onEditWishlist
                )

            is WishlistsUiState.EmptyWishlistOpen ->
                WishlistOpenedEmptyScreen(
                    uiState = screenUiState,
                    onCreateItem = onCreateWishlistItem,
                    onCloseWishlist = onCloseWishlist
                )

            is WishlistsUiState.WishlistOpen ->
                WishlistOpenedScreen(
                    uiState = screenUiState,
                    onCreateItem = onCreateWishlistItem,
                    onCloseWishlist = onCloseWishlist,
                    onWishlistItemClick = onWishlistItemClick
                )

            is WishlistsUiState.WishlistOpenEditing -> {
            }

            is WishlistsUiState.WishlistItemOpen ->
                WishlistItemOpenedScreen(
                    uiState = screenUiState,
                    onCloseWishlistItem = onCloseWishlistItem,
                    onUpdateItem = onUpdateWishlistItem
                )
        }
    }
}
