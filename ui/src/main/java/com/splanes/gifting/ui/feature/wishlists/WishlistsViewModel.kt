package com.splanes.gifting.ui.feature.wishlists

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistItemRequest
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import com.splanes.gifting.domain.feature.list.wishlist.usecase.AddWishlistItemUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.CreateWishlistUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.GetWishlistsUseCase
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update

data class WishlistsUiViewModelState(
    private val error: ErrorVisuals = ErrorVisuals.Empty,
    private val loading: LoadingVisuals = LoadingVisuals.Hidden,
    val wishlists: List<Wishlist> = emptyList(),
    val wishlist: Wishlist? = null,
    private val isNewWishlistOpen: Boolean = false,
    private val isNewItemOpen: Boolean = false,
    private val isDeleteOpen: Boolean = false,
    val wishlistsSelected: List<Wishlist> = emptyList(),
    val wishlistItemsSelected: List<WishlistItem> = emptyList()
) : UiViewModelState<WishlistsUiState> {
    override fun toUiState(): WishlistsUiState =
        when {
            wishlist != null -> {
                when {
                    wishlist.items.isNotEmpty() -> {
                        if (wishlistItemsSelected.isEmpty()) {
                            WishlistsUiState.WishlistOpen(
                                loading = loading,
                                error = error,
                                wishlist = wishlist,
                                isNewItemOpen = isNewItemOpen
                            )
                        } else {
                            WishlistsUiState.WishlistOpenEditing(
                                loading = loading,
                                error = error,
                                wishlist = wishlist,
                                isDeleteDialogOpen = isDeleteOpen,
                                itemsSelected = wishlistItemsSelected
                            )
                        }
                    }

                    else ->
                        WishlistsUiState.EmptyWishlistOpen(
                            loading = loading,
                            error = error,
                            wishlist = wishlist,
                            isNewItemOpen = isNewItemOpen
                        )
                }
            }

            wishlists.isNotEmpty() -> {
                if (wishlistItemsSelected.isEmpty()) {
                    WishlistsUiState.Wishlists(
                        loading = loading,
                        error = error,
                        wishlists = wishlists,
                        isNewWishlistOpen = isNewWishlistOpen
                    )
                } else {
                    WishlistsUiState.WishlistsEditing(
                        loading = loading,
                        error = error,
                        wishlists = wishlists,
                        wishlistsSelected = wishlistsSelected,
                        isDeleteDialogOpen = isDeleteOpen
                    )
                }
            }

            else -> {
                WishlistsUiState.EmptyWishlists(
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
    private val createWishlist: CreateWishlistUseCase,
    private val addWishlistItem: AddWishlistItemUseCase
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

    fun onNewWishlistItem() {
        viewModelState.update { state -> state.copy(isNewItemOpen = true) }
    }

    fun onDismissNewWishlist() {
        viewModelState.update { state -> state.copy(isNewWishlistOpen = false) }
    }

    fun onDismissNewWishlistItem() {
        viewModelState.update { state -> state.copy(isNewItemOpen = false) }
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

    fun openDeleteWishlistDialog() {
        viewModelState.update { state -> state.copy(isDeleteOpen = true) }
    }

    fun openDeleteWishlistItemDialog() {
        viewModelState.update { state -> state.copy(isDeleteOpen = true) }
    }

    fun onDeleteWishlist(wishlist: Wishlist) {
        // todo
    }

    fun onDeleteWishlistItem(item: WishlistItem) {
        // todo
    }

    fun onSelectWishlist(wishlist: Wishlist) {
        viewModelState.update { state ->
            state.copy(
                wishlistsSelected = state.wishlistsSelected + wishlist
            )
        }
    }

    fun onUnselectWishlist(wishlist: Wishlist) {
        viewModelState.update { state ->
            state.copy(
                wishlistsSelected = state.wishlistsSelected - wishlist
            )
        }
    }

    fun onSelectWishlistItem(item: WishlistItem) {
        viewModelState.update { state ->
            state.copy(
                wishlistItemsSelected = state.wishlistItemsSelected + item
            )
        }
    }

    fun onUnselectWishlistItem(item: WishlistItem) {
        viewModelState.update { state ->
            state.copy(
                wishlistItemsSelected = state.wishlistItemsSelected - item
            )
        }
    }

    fun onCloseWishlist() {
        viewModelState.update { state -> state.copy(wishlist = null) }
    }

    fun onCreateWishlistItem(form: WishlistItemFormResultData) {
        viewModelState.update { state ->
            state.copy(loading = LoadingVisuals.Visible)
        }
        launch {
            viewModelState.value.wishlist?.let {
                val request = NewWishlistItemRequest(
                    wishlist = it,
                    name = form.name,
                    description = form.description,
                    price = form.price,
                    url = form.url,
                    notes = form.notes,
                    categories = form.categories,
                    tags = form.tags
                )
                addWishlistItem(request)
                    .then { wishlist ->
                        viewModelState.update { state ->
                            state.copy(
                                loading = LoadingVisuals.Hidden,
                                wishlist = wishlist
                            )
                        }
                    }
                    .catch {
                        viewModelState.update { state ->
                            state.copy(
                                loading = LoadingVisuals.Hidden
                                // Todo error
                            )
                        }
                    }
            }
        }
    }
}
