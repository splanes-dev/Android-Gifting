package com.splanes.gifting.ui.feature.wishlists.components

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.primitives.ifNotBlank
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun WishlistsGrid(
    wishlists: List<Wishlist>,
    onWishlistClick: (Wishlist) -> Unit,
    onWishlistLongClick: (Wishlist) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        itemsIndexed(wishlists) { index, wishlist ->
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                WishlistGridItem(
                    wishlist = wishlist,
                    onClick = { onWishlistClick(wishlist) },
                    onLongClick = { onWishlistLongClick(wishlist) }
                )
                if (index != wishlists.lastIndex) {
                    Spacer(height = 16.dp)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                        color = colorOf { primary.withAlpha(.1) }
                    )
                }
            }
        }
    }
}

@Composable
fun WishlistEditGrid(
    wishlists: List<Wishlist>,
    selected: List<Wishlist>,
    onWishlistClick: (Wishlist) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        itemsIndexed(wishlists) { index, wishlist ->
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                WishlistGridEditItem(
                    wishlist = wishlist,
                    isSelected = selected.contains(wishlist),
                    onClick = { onWishlistClick(wishlist) }
                )
                if (index != wishlists.lastIndex) {
                    Spacer(height = 16.dp)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                        color = colorOf { primary.withAlpha(.1) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WishlistGridItem(
    wishlist: Wishlist,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        WishlistItemContent(
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Rounded.ReceiptLong,
                    contentDescription = wishlist.name,
                    tint = colorOf { tertiary.withAlpha(.7) }
                )
            },
            name = wishlist.name,
            description = wishlist.description,
            count = wishlist.items.count()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistGridEditItem(
    wishlist: Wishlist,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp)
    ) {
        WishlistItemContent(
            icon = {
                Crossfade(targetState = isSelected) { selected ->
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = if (selected) {
                                Icons.Rounded.RadioButtonChecked
                            } else {
                                Icons.Rounded.RadioButtonUnchecked
                            },
                            contentDescription = wishlist.name,
                            tint = colorOf {
                                if (selected) {
                                    primary
                                } else {
                                    tertiary.withAlpha(.7)
                                }
                            }
                        )
                    }
                }
            },
            name = wishlist.name,
            description = wishlist.description,
            count = wishlist.items.count()
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WishlistItemContent(
    icon: @Composable () -> Unit,
    name: String,
    description: String?,
    count: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()

        Spacer(width = 16.dp)

        Column {
            Text(
                text = name,
                style = textStyleOf { titleLarge }
            )
            description?.ifNotBlank {
                Spacer(height = 4.dp)
                Text(
                    text = description,
                    style = textStyleOf { bodySmall },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Weight()

        Text(
            text = pluralStringResource(
                id = R.plurals.wishlist_item_count,
                count = count,
                count
            ),
            style = textStyleOf { labelMedium },
            color = colorOf { onSurface.withAlpha(.7) }

        )
    }
}

@Preview("Wishlist grid", device = Devices.PIXEL_C)
@Preview("Wishlist grid (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Wishlist grid (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
private fun WishlistGridPreview() {
    GiftingTheme {
        WishlistsGrid(
            wishlists = listOf(
                Wishlist(
                    id = "",
                    name = "Tech list",
                    description = "Tech item that I would like to have",
                    items = emptyList(),
                    owner = "",
                    createdOn = 0L,
                    updatedOn = 0L
                ),
                Wishlist(
                    id = "",
                    name = "Home list",
                    description = null,
                    items = listOf(
                        WishlistItem(
                            wishlistId = "",
                            id = "",
                            owner = "",
                            name = "",
                            addedOn = 0L
                        ),
                        WishlistItem(
                            wishlistId = "",
                            id = "",
                            owner = "",
                            name = "",
                            addedOn = 0L
                        ),
                        WishlistItem(
                            wishlistId = "",
                            id = "",
                            owner = "",
                            name = "",
                            addedOn = 0L
                        )
                    ),
                    owner = "",
                    createdOn = 0L,
                    updatedOn = 0L
                )
            ),
            onWishlistClick = {},
            onWishlistLongClick = {}
        )
    }
}
