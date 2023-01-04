package com.splanes.gifting.ui.feature.wishlists

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiState

sealed interface WishlistsUiState : UiState {
    data class EmptyWishlists(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val isNewWishlistOpen: Boolean = false
    ) : WishlistsUiState

    data class Wishlists(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val isNewWishlistOpen: Boolean = false,
        val wishlists: List<Wishlist>
    ) : WishlistsUiState

    data class WishlistsEditing(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlistsSelected: List<Wishlist> = emptyList(),
        val isDeleteDialogOpen: Boolean = false,
        val wishlists: List<Wishlist>
    ) : WishlistsUiState

    data class WishlistOpen(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlist: Wishlist,
        val isNewItemOpen: Boolean = false
    ) : WishlistsUiState

    data class EmptyWishlistOpen(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlist: Wishlist,
        val isNewItemOpen: Boolean = false
    ) : WishlistsUiState

    data class WishlistOpenEditing(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val isDeleteDialogOpen: Boolean = false,
        val itemsSelected: List<WishlistItem> = emptyList(),
        val wishlist: Wishlist
    ) : WishlistsUiState
}
