package com.splanes.gifting.ui.feature.wishlists

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.West
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.bottomsheet.BottomSheetLayout
import com.splanes.gifting.ui.common.components.bottomsheet.expand
import com.splanes.gifting.ui.common.components.bottomsheet.rememberBottomSheetState
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingIconButton
import com.splanes.gifting.ui.common.components.dialog.DialogButtonUi
import com.splanes.gifting.ui.common.components.dialog.GiftingDialog
import com.splanes.gifting.ui.common.components.dialog.rememberDialogState
import com.splanes.gifting.ui.common.components.emptystate.EmptyState
import com.splanes.gifting.ui.common.components.loader.LoaderScaffold
import com.splanes.gifting.ui.common.components.spacer.column.Weight
import com.splanes.gifting.ui.common.components.topbar.GiftingTopBar
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.remember.rememberStateOf
import com.splanes.gifting.ui.common.utils.url.openUrl
import com.splanes.gifting.ui.feature.wishlists.components.OnWishlistFormButtonClick
import com.splanes.gifting.ui.feature.wishlists.components.WishlistEditButtons
import com.splanes.gifting.ui.feature.wishlists.components.WishlistEditGrid
import com.splanes.gifting.ui.feature.wishlists.components.WishlistForm
import com.splanes.gifting.ui.feature.wishlists.components.WishlistItemEditForm
import com.splanes.gifting.ui.feature.wishlists.components.WishlistItemForm
import com.splanes.gifting.ui.feature.wishlists.components.WishlistItemsList
import com.splanes.gifting.ui.feature.wishlists.components.WishlistItemsListEditing
import com.splanes.gifting.ui.feature.wishlists.components.WishlistsGrid
import com.splanes.gifting.ui.feature.wishlists.model.WishlistFormResultData
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WishlistsEmptyScreen(
    uiState: WishlistsUiState.EmptyWishlists,
    onCreateWishlist: (WishlistFormResultData) -> Unit
) {
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(state = bottomSheetState, modalContent = {
            WishlistForm(
                onButtonClick = OnWishlistFormButtonClick.Create(onCreateWishlist),
                onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
            )
        }) {
            Scaffold(topBar = {
                GiftingTopBar(title = stringResource(id = R.string.wishlists))
            }) { innerPaddings ->
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
    onCreateWishlist: (WishlistFormResultData) -> Unit,
    onWishlistClick: (Wishlist) -> Unit,
    onWishlistLongClick: (Wishlist) -> Unit
) {
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(state = bottomSheetState, modalContent = {
            WishlistForm(
                onButtonClick = OnWishlistFormButtonClick.Create(onCreateWishlist),
                onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
            )
        }) {
            Scaffold(topBar = {
                GiftingTopBar(title = stringResource(id = R.string.wishlists))
            }) { innerPaddings ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPaddings)
                ) {
                    WishlistsGrid(
                        modifier = Modifier.padding(top = 16.dp),
                        wishlists = uiState.wishlists,
                        onWishlistClick = onWishlistClick,
                        onWishlistLongClick = onWishlistLongClick
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistGridEditScreen(
    uiState: WishlistsUiState.WishlistsEditing,
    onSelectWishlist: (Wishlist) -> Unit,
    onUnselectWishlist: (Wishlist) -> Unit,
    onDeleteWishlist: (List<Wishlist>) -> Unit,
    onEditWishlist: (WishlistFormResultData) -> Unit
) {
    val selected = uiState.wishlistsSelected
    val dialogState = rememberDialogState()
    var wishlistToEdit: Wishlist? by rememberStateOf(value = null)

    LoaderScaffold(uiState = uiState) {
        Scaffold(topBar = {
            GiftingTopBar(
                title = stringResource(id = R.string.wishlists)
            )
        }) { innerPaddings ->
            Crossfade(wishlistToEdit) { wishlist ->
                if (wishlist == null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPaddings)
                    ) {
                        WishlistEditGrid(
                            modifier = Modifier.padding(top = 16.dp),
                            wishlists = uiState.wishlists,
                            selected = selected,
                            onWishlistClick = { wishlist ->
                                if (selected.contains(wishlist)) {
                                    onUnselectWishlist(wishlist)
                                } else {
                                    onSelectWishlist(wishlist)
                                }
                            }
                        )

                        WishlistEditButtons(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp),
                            editable = selected.count() == 1,
                            onEdit = { wishlistToEdit = selected.first() },
                            onDelete = { dialogState.show() }
                        )
                    }
                } else {
                    WishlistForm(
                        initialName = wishlist.name,
                        initialDescription = wishlist.description.orEmpty(),
                        onButtonClick = OnWishlistFormButtonClick.Edit { onEditWishlist(it) },
                        onDismiss = { wishlistToEdit = null }
                    )
                }
            }
        }
    }

    GiftingDialog(
        dialogState = dialogState,
        title = stringResource(id = R.string.wishlists_delete_confirmation_title),
        confirmButton = DialogButtonUi(
            text = stringResource(id = R.string.delete),
            onClick = { onDeleteWishlist(selected) }
        ),
        dismissButton = DialogButtonUi(text = stringResource(id = R.string.cancel))
    ) {
        Text(
            text = stringResource(
                id = R.string.wishlists_delete_confirmation_text,
                selected.map { it.name }.joinToString(separator = "") { "· $it\n" }
            )
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WishlistOpenedEmptyScreen(
    uiState: WishlistsUiState.EmptyWishlistOpen,
    onCreateItem: (WishlistItemFormResultData) -> Unit,
    onCloseWishlist: () -> Unit
) {
    val wishlist = uiState.wishlist
    val bottomSheetState = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    LoaderScaffold(uiState = uiState) {
        BottomSheetLayout(state = bottomSheetState, modalContent = {
            WishlistItemForm(
                modifier = Modifier.padding(horizontal = 16.dp),
                onButtonClick = onCreateItem,
                onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
            )
        }) {
            Scaffold(topBar = {
                GiftingTopBar(title = wishlist.name, navigationIcon = {
                    GiftingIconButton(
                        imageVector = Icons.Rounded.West,
                        tint = colorOf { onPrimaryContainer },
                        onClick = onCloseWishlist
                    )
                })
            }) { innerPaddings ->
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
    onWishlistItemClick: (WishlistItem) -> Unit,
    onWishlistItemLongClick: (WishlistItem) -> Unit,
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
                WishlistItemForm(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onButtonClick = onCreateItem,
                    onDismiss = { coroutineScope.launch { bottomSheetState.hide() } }
                )
            }
        ) {
            Scaffold(topBar = {
                GiftingTopBar(title = wishlist.name, navigationIcon = {
                    GiftingIconButton(
                        imageVector = Icons.Rounded.West,
                        tint = colorOf { onPrimaryContainer },
                        onClick = onCloseWishlist
                    )
                })
            }) { innerPaddings ->
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
                        onItemClick = onWishlistItemClick,
                        onItemLongClick = onWishlistItemLongClick,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistOpenedEditScreen(
    uiState: WishlistsUiState.WishlistOpenEditing,
    onCloseWishlist: () -> Unit,
    onSelectWishlistItem: (WishlistItem) -> Unit,
    onUnselectWishlistItem: (WishlistItem) -> Unit,
    onDeleteWishlistItems: (List<WishlistItem>) -> Unit
) {
    val wishlist = uiState.wishlist
    val context = LocalContext.current

    LoaderScaffold(uiState = uiState) {
        Scaffold(topBar = {
            GiftingTopBar(title = wishlist.name, navigationIcon = {
                GiftingIconButton(
                    imageVector = Icons.Rounded.West,
                    tint = colorOf { onPrimaryContainer },
                    onClick = onCloseWishlist
                )
            })
        }) { innerPaddings ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPaddings)
            ) {
                WishlistItemsListEditing(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 48.dp),
                    items = wishlist.items,
                    selected = uiState.itemsSelected,
                    onItemClick = { item ->
                        if (uiState.itemsSelected.contains(item)) {
                            onUnselectWishlistItem(item)
                        } else {
                            onSelectWishlistItem(item)
                        }
                    },
                    onUrlClick = context::openUrl
                )

                GiftingButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    text = stringResource(id = R.string.delete),
                    leadingIcon = Icons.Rounded.DeleteForever,
                    onClick = { onDeleteWishlistItems(uiState.itemsSelected) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistItemOpenedScreen(
    uiState: WishlistsUiState.WishlistItemOpen,
    onCloseWishlistItem: () -> Unit,
    onUpdateItem: (WishlistItem, WishlistItemFormResultData) -> Unit
) {
    LoaderScaffold(uiState = uiState) {
        Scaffold(
            topBar = {
                GiftingTopBar(title = uiState.wishlist.name, navigationIcon = {
                    GiftingIconButton(
                        imageVector = Icons.Rounded.West,
                        tint = colorOf { onPrimaryContainer },
                        onClick = onCloseWishlistItem
                    )
                })
            }
        ) { innerPaddings ->
            WishlistItemEditForm(
                modifier = Modifier.padding(innerPaddings),
                item = uiState.item,
                onButtonClick = { form -> onUpdateItem(uiState.item, form) }
            )
        }
    }
}
