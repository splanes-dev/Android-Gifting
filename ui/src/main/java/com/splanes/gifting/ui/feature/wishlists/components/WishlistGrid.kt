package com.splanes.gifting.ui.feature.wishlists.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun WishlistsGrid(
    wishlists: List<Wishlist>,
    onWishlistClick: (Wishlist) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        itemsIndexed(wishlists) { index, wishlist ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                WishlistGridItem(wishlist = wishlist) {
                    onWishlistClick(wishlist)
                }
                if (index != wishlists.lastIndex) {
                    Spacer(height = 16.dp)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp),
                        color = colorOf { primary.withAlpha(.3) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WishlistGridItem(
    wishlist: Wishlist,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Rounded.ReceiptLong,
            contentDescription = wishlist.name,
            tint = colorOf { tertiary.withAlpha(.7) }
        )

        Spacer(width = 16.dp)

        Column {
            Text(
                text = wishlist.name,
                style = textStyleOf { titleLarge }
            )
            wishlist.description.takeIf { !it.isNullOrBlank() }?.let { description ->
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
                wishlist.items.count(),
                wishlist.items.count()
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
            onWishlistClick = {}
        )
    }
}
