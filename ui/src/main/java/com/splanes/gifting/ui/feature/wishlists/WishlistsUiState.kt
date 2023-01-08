package com.splanes.gifting.ui.feature.wishlists

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiState

sealed interface WishlistsUiState : UiState {
    data class EmptyWishlists(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals
    ) : WishlistsUiState

    data class Wishlists(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlists: List<Wishlist>
    ) : WishlistsUiState

    data class WishlistsEditing(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlistsSelected: List<Wishlist> = emptyList(),
        val wishlists: List<Wishlist>
    ) : WishlistsUiState

    data class WishlistOpen(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlist: Wishlist
    ) : WishlistsUiState

    data class EmptyWishlistOpen(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val wishlist: Wishlist
    ) : WishlistsUiState

    data class WishlistOpenEditing(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val itemsSelected: List<WishlistItem> = emptyList(),
        val wishlist: Wishlist
    ) : WishlistsUiState
}
