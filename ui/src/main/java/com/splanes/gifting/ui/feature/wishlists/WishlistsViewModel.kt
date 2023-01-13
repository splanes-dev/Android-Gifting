package com.splanes.gifting.ui.feature.wishlists

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistItemRequest
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import com.splanes.gifting.domain.feature.list.wishlist.request.UpdateWishlistItemRequest
import com.splanes.gifting.domain.feature.list.wishlist.request.UpdateWishlistRequest
import com.splanes.gifting.domain.feature.list.wishlist.usecase.AddWishlistItemUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.CreateWishlistUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.DeleteWishlistsUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.GetWishlistsUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.UpdateWishlistItemUseCase
import com.splanes.gifting.domain.feature.list.wishlist.usecase.UpdateWishlistUseCase
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import com.splanes.gifting.ui.feature.wishlists.model.WishlistFormResultData
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update

data class WishlistsUiViewModelState(
    private val error: ErrorVisuals = ErrorVisuals.Empty,
    private val loading: LoadingVisuals = LoadingVisuals.Hidden,
    val wishlists: List<Wishlist> = emptyList(),
    val wishlist: Wishlist? = null,
    val itemDetails: WishlistItem? = null,
    val wishlistsSelected: List<Wishlist> = emptyList(),
    val wishlistItemsSelected: List<WishlistItem> = emptyList()
) : UiViewModelState<WishlistsUiState> {
    override fun toUiState(): WishlistsUiState =
        when {
            wishlist != null -> {
                when {
                    itemDetails != null -> {
                        WishlistsUiState.WishlistItemOpen(
                            loading = loading,
                            error = error,
                            wishlist = wishlist,
                            item = itemDetails
                        )
                    }

                    wishlist.items.isNotEmpty() -> {
                        if (wishlistItemsSelected.isEmpty()) {
                            WishlistsUiState.WishlistOpen(
                                loading = loading,
                                error = error,
                                wishlist = wishlist
                            )
                        } else {
                            WishlistsUiState.WishlistOpenEditing(
                                loading = loading,
                                error = error,
                                wishlist = wishlist,
                                itemsSelected = wishlistItemsSelected
                            )
                        }
                    }

                    else ->
                        WishlistsUiState.EmptyWishlistOpen(
                            loading = loading,
                            error = error,
                            wishlist = wishlist
                        )
                }
            }

            wishlists.isNotEmpty() -> {
                if (wishlistsSelected.isEmpty()) {
                    WishlistsUiState.Wishlists(
                        loading = loading,
                        error = error,
                        wishlists = wishlists
                    )
                } else {
                    WishlistsUiState.WishlistsEditing(
                        loading = loading,
                        error = error,
                        wishlists = wishlists,
                        wishlistsSelected = wishlistsSelected
                    )
                }
            }

            else -> {
                WishlistsUiState.EmptyWishlists(
                    loading = loading,
                    error = error
                )
            }
        }
}

@HiltViewModel
class WishlistsViewModel @Inject constructor(
    private val getWishlists: GetWishlistsUseCase,
    private val createWishlist: CreateWishlistUseCase,
    private val deleteWishlists: DeleteWishlistsUseCase,
    private val updateWishlist: UpdateWishlistUseCase,
    private val addWishlistItem: AddWishlistItemUseCase,
    private val updateWishlistItem: UpdateWishlistItemUseCase
) : UiViewModel<WishlistsUiState, WishlistsUiViewModelState>(WishlistsUiViewModelState()) {

    init {
        launchGetWishlists()
    }

    private fun launchGetWishlists() {
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

    fun onCreateWishlist(form: WishlistFormResultData) {
        viewModelState.update { state ->
            state.copy(loading = LoadingVisuals.Visible)
        }
        launch {
            val request = NewWishlistRequest(
                name = form.name,
                description = form.description
            )
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

    fun onEditWishlist(form: WishlistFormResultData) {
        viewModelState.update { state ->
            state.copy(loading = LoadingVisuals.Visible)
        }
        launch {
            val request = UpdateWishlistRequest(
                wishlist = viewModelState.value.wishlistsSelected.first(),
                name = form.name,
                description = form.description
            )
            updateWishlist(request)
                .then { wishlist ->
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden,
                            wishlists = state.wishlists.filter { w ->
                                w.id != wishlist.id
                            } + wishlist,
                            wishlistsSelected = emptyList()
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

    fun openWishlist(wishlist: Wishlist) {
        viewModelState.update { state -> state.copy(wishlist = wishlist) }
    }

    fun openWishlistItem(item: WishlistItem) {
        viewModelState.update { state -> state.copy(itemDetails = item) }
    }

    fun onDeleteWishlists(wishlists: List<Wishlist>) {
        viewModelState.update { state ->
            state.copy(loading = LoadingVisuals.Visible)
        }
        launch {
            deleteWishlists(wishlists)
                .then { removed ->
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden,
                            wishlists = state.wishlists - removed.toSet(),
                            wishlistsSelected = state.wishlistsSelected - removed.toSet()
                        )
                    }
                }
                .catch {
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden
                            // error = todo
                        )
                    }
                }
        }
    }

    fun onDeleteWishlistItem(items: List<WishlistItem>) {
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
        viewModelState.update { state ->
            state.copy(
                wishlist = null,
                wishlistItemsSelected = emptyList()
            )
        }
    }

    fun onCloseWishlistItem() {
        viewModelState.update { state -> state.copy(itemDetails = null) }
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
                                wishlist = wishlist,
                                wishlists = state.wishlists.filter { w ->
                                    w.id != wishlist.id
                                } + wishlist
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

    fun onUpdateWishlistItem(item: WishlistItem, form: WishlistItemFormResultData) {
        viewModelState.update { state ->
            state.copy(loading = LoadingVisuals.Visible)
        }
        launch {
            viewModelState.value.wishlist?.let {
                val request = UpdateWishlistItemRequest(
                    wishlist = it,
                    item = item.copy(
                        name = form.name,
                        description = form.description,
                        price = form.price,
                        url = form.url,
                        categories = form.categories,
                        tags = form.tags,
                        notes = form.notes
                    )
                )
                updateWishlistItem(request)
                    .then { wishlist ->
                        viewModelState.update { state ->
                            state.copy(
                                loading = LoadingVisuals.Hidden,
                                wishlist = wishlist,
                                wishlists = state.wishlists.filter { w ->
                                    w.id != wishlist.id
                                } + wishlist,
                                itemDetails = null
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
