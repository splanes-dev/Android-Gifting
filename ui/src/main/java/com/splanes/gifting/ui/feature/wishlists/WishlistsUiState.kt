package com.splanes.gifting.ui.feature.wishlists

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiState

sealed interface WishlistsUiState : UiState {

    val isNewWishlistOpen: Boolean

    data class Empty(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val isNewWishlistOpen: Boolean = false
    ) : WishlistsUiState

    data class Wishlists(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val isNewWishlistOpen: Boolean = false,
        val wishlists: List<Wishlist>
    ) : WishlistsUiState

    data class WishlistOpened(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val isNewWishlistOpen: Boolean = false,
        val wishlist: Wishlist,
        val isNewItemOpen: Boolean = false
    ) : WishlistsUiState
}
