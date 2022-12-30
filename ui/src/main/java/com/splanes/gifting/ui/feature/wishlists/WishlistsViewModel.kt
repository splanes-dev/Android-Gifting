package com.splanes.gifting.ui.feature.wishlists

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import com.splanes.gifting.domain.feature.list.wishlist.usecase.CreateWishlistUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.GetWishlistsUseCase
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update

data class WishlistsUiViewModelState(
    private val error: ErrorVisuals = ErrorVisuals.Empty,
    private val loading: LoadingVisuals = LoadingVisuals.Hidden,
    val wishlists: List<Wishlist> = emptyList(),
    val wishlist: Wishlist? = null,
    private val isNewWishlistOpen: Boolean = false,
    private val isNewItemOpen: Boolean = false
) : UiViewModelState<WishlistsUiState> {
    override fun toUiState(): WishlistsUiState =
        when {
            wishlist != null -> {
                WishlistsUiState.WishlistOpened(
                    loading = loading,
                    error = error,
                    wishlist = wishlist,
                    isNewWishlistOpen = isNewWishlistOpen,
                    isNewItemOpen = isNewItemOpen
                )
            }

            wishlists.isNotEmpty() -> {
                WishlistsUiState.Wishlists(
                    loading = loading,
                    error = error,
                    wishlists = wishlists,
                    isNewWishlistOpen = isNewWishlistOpen
                )
            }

            else -> {
                WishlistsUiState.Empty(
                    loading = loading,
                    error = error,
                    isNewWishlistOpen = isNewWishlistOpen
                )
            }
        }
}

@HiltViewModel
class WishlistsViewModel @Inject constructor(
    private val getWishlists: GetWishlistsUseCase,
    private val createWishlist: CreateWishlistUseCase
) : UiViewModel<WishlistsUiState, WishlistsUiViewModelState>(
    WishlistsUiViewModelState()
) {

    init {
        viewModelState.update { state ->
            state.copy(loading = LoadingVisuals.Visible)
        }
        launch {
            getWishlists()
                .then { wishlists ->
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden,
                            wishlists = wishlists
                        )
                    }
                }
                .catch {
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden
                            // error = TODO
                        )
                    }
                }
        }
    }

    fun onNewWishlist() {
        viewModelState.update { state -> state.copy(isNewWishlistOpen = true) }
    }

    fun onDismissNewWishlist() {
        viewModelState.update { state -> state.copy(isNewWishlistOpen = false) }
    }

    fun onCreateWishlist(request: NewWishlistRequest) {
        viewModelState.update { state ->
            state.copy(
                loading = LoadingVisuals.Visible,
                isNewWishlistOpen = false
            )
        }
        launch {
            createWishlist(request)
                .then { wishlist ->
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden,
                            wishlists = state.wishlists + wishlist
                        )
                    }
                }
                .catch {
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden
                            // error = TODO
                        )
                    }
                }
        }
    }

    fun openWishlist(wishlist: Wishlist) {
        viewModelState.update { state -> state.copy(wishlist = wishlist) }
    }
}
