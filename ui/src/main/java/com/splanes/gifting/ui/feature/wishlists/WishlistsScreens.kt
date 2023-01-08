package com.splanes.gifting.ui.feature.wishlists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.West
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.bottomsheet.BottomSheetLayout
import com.splanes.gifting.ui.common.components.bottomsheet.expand
import com.splanes.gifting.ui.common.components.bottomsheet.rememberBottomSheetState
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingIconButton
import com.splanes.gifting.ui.common.components.emptystate.EmptyState
import com.splanes.gifting.ui.common.components.loader.LoaderScaffold
import com.splanes.gifting.ui.common.components.spacer.column.Weight
import com.splanes.gifting.ui.common.components.topbar.GiftingTopBar
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.url.openUrl
import com.splanes.gifting.ui.feature.wishlists.components.OnWishlistFormButtonClick
import com.splanes.gifting.ui.feature.wishlists.components.WishlistCreateItemForm
import com.splanes.gifting.ui.feature.wishlists.components.WishlistForm
import com.splanes.gifting.ui.feature.wishlists.components.WishlistItemsList
import com.splanes.gifting.ui.feature.wishlists.components.WishlistsGrid
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WishlistsEmptyScreen(
    uiState: WishlistsUiState.EmptyWishlists,
    onCreateWishlist: (NewWishlistRequest) -> Unit
) {
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(
            state = bottomSheetState,
            modalContent = {
                WishlistForm(
                    onButtonClick = OnWishlistFormButtonClick.Create(onCreateWishlist),
                    onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    GiftingTopBar(title = stringResource(id = R.string.wishlists))
                }
            ) { innerPaddings ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPaddings),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Weight(.5)

                    EmptyState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        title = stringResource(id = R.string.wishlists_empty_title),
                        description = stringResource(id = R.string.wishlists_empty_description)
                    )

                    Weight()

                    GiftingButton(
                        modifier = Modifier.wrapContentSize(),
                        text = stringResource(id = R.string.wishlist_create_button),
                        onClick = { coroutineScope.launch { bottomSheetState.expand() } }
                    )

                    Weight(.5)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WishlistGridScreen(
    uiState: WishlistsUiState.Wishlists,
    onCreateWishlist: (NewWishlistRequest) -> Unit,
    onWishlistClick: (Wishlist) -> Unit
) {
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(
            state = bottomSheetState,
            modalContent = {
                WishlistForm(
                    onButtonClick = OnWishlistFormButtonClick.Create(onCreateWishlist),
                    onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    GiftingTopBar(title = stringResource(id = R.string.wishlists))
                }
            ) { innerPaddings ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPaddings)
                ) {
                    WishlistsGrid(
                        modifier = Modifier.padding(top = 16.dp),
                        wishlists = uiState.wishlists,
                        onWishlistClick = onWishlistClick
                    )

                    GiftingButton(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        text = stringResource(id = R.string.wishlist_create_button),
                        onClick = { coroutineScope.launch { bottomSheetState.expand() } }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EmptyWishlistOpenedScreen(
    uiState: WishlistsUiState.EmptyWishlistOpen,
    onCreateItem: (WishlistItemFormResultData) -> Unit,
    onCloseWishlist: () -> Unit
) {
    val wishlist = uiState.wishlist
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(
            state = bottomSheetState,
            modalContent = {
                WishlistCreateItemForm(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onCreate = onCreateItem,
                    onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    GiftingTopBar(
                        title = wishlist.name,
                        navigationIcon = {
                            GiftingIconButton(
                                imageVector = Icons.Rounded.West,
                                tint = colorOf { onPrimaryContainer },
                                onClick = onCloseWishlist
                            )
                        }
                    )
                }
            ) { innerPaddings ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPaddings),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Weight(.5)

                    EmptyState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        title = stringResource(id = R.string.wishlist_items_empty_title),
                        description = stringResource(id = R.string.wishlist_items_empty_description)
                    )

                    Weight()

                    GiftingButton(
                        text = stringResource(id = R.string.wishlist_add_item),
                        onClick = { coroutineScope.launch { bottomSheetState.expand() } }
                    )

                    Weight(.5)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun WishlistOpenedScreen(
    uiState: WishlistsUiState.WishlistOpen,
    onCreateItem: (WishlistItemFormResultData) -> Unit,
    onCloseWishlist: () -> Unit
) {
    val wishlist = uiState.wishlist
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(
            state = bottomSheetState,
            modalContent = {
                WishlistCreateItemForm(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onCreate = onCreateItem,
                    onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    GiftingTopBar(
                        title = wishlist.name,
                        navigationIcon = {
                            GiftingIconButton(
                                imageVector = Icons.Rounded.West,
                                tint = colorOf { onPrimaryContainer },
                                onClick = onCloseWishlist
                            )
                        }
                    )
                }
            ) { innerPaddings ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPaddings)
                ) {
                    WishlistItemsList(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 48.dp),
                        items = wishlist.items,
                        onItemClick = { /* todo */ },
                        onUrlClick = { url -> context.openUrl(url) }
                    )

                    GiftingButton(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp),
                        text = stringResource(id = R.string.wishlist_add_item),
                        onClick = { coroutineScope.launch { bottomSheetState.expand() } }
                    )
                }
            }
        }
    }
}
