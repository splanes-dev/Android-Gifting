package com.splanes.gifting.ui.feature.wishlists.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.domain.feature.list.model.GiftPrice
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.giftcategory.icon
import com.splanes.gifting.ui.common.utils.primitives.ifNotBlank
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun WishlistItemsList(
    items: List<WishlistItem>,
    onItemClick: (WishlistItem) -> Unit,
    onItemLongClick: (WishlistItem) -> Unit,
    onUrlClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            WishlistItemContent(
                item = item,
                onUrlClick = { item.url?.ifNotBlank(onUrlClick) },
                onClick = { onItemClick(item) },
                onLongClick = { onItemLongClick(item) }
            )
        }
    }
}

@Composable
fun WishlistItemsListEditing(
    items: List<WishlistItem>,
    selected: List<WishlistItem>,
    onItemClick: (WishlistItem) -> Unit,
    onUrlClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            val isSelected = selected.contains(item)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(width = 16.dp)

                Crossfade(targetState = isSelected) { itemSelected ->
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = if (itemSelected) {
                            Icons.Rounded.RadioButtonChecked
                        } else {
                            Icons.Rounded.RadioButtonUnchecked
                        },
                        contentDescription = item.name,
                        tint = colorOf {
                            if (itemSelected) {
                                primary
                            } else {
                                tertiary.withAlpha(.7)
                            }
                        }
                    )
                }

                WishlistItemContent(
                    item,
                    onClick = { onItemClick(item) },
                    onUrlClick = { item.url?.ifNotBlank(onUrlClick) },
                    onLongClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WishlistItemContent(
    item: WishlistItem,
    onUrlClick: () -> Unit,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        WishlistItemBasicInfo(item)

        if (!item.notes.isNullOrBlank() || item.categories.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = if (item.url.isNullOrBlank()) 16.dp else 0.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val categories = item.categories.filter { it != GiftCategory.Undefined }
                categories.forEachIndexed { index, category ->
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .alpha(.7f),
                        imageVector = category.icon(),
                        contentDescription = category.name
                    )

                    if (index != categories.lastIndex) {
                        Spacer(width = 8.dp)
                    }
                }
                if (!item.notes.isNullOrBlank()) {
                    if (item.categories.isNotEmpty()) {
                        Spacer(width = 12.dp)

                        Divider(
                            modifier = Modifier
                                .height(16.dp)
                                .width(1.dp),
                            color = colorOf { onSurface.withAlpha(.1) }
                        )

                        Spacer(width = 12.dp)
                    }
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .alpha(.7f),
                        imageVector = Icons.Rounded.Notes,
                        contentDescription = stringResource(id = R.string.wishlist_notes)
                    )
                }

                Weight()

                if (!item.url.isNullOrBlank()) {
                    GiftingTextButton(
                        text = "See on web",
                        textStyle = textStyleOf { labelSmall },
                        onClick = onUrlClick
                    )
                }
            }

            Spacer(height = 6.dp)
        }
    }
}

@Composable
private fun WishlistItemBasicInfo(item: WishlistItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = item.name,
                style = textStyleOf { titleLarge }
            )

            item.description?.let { description ->
                Spacer(height = 4.dp)

                Text(
                    text = description,
                    style = textStyleOf { bodySmall },
                    color = colorOf { onSurface.withAlpha(.6) }
                )
            }
        }

        Weight()

        item.price?.let { price ->
            Text(
                text = price.formatted,
                style = textStyleOf { headlineMedium }
            )
        }
    }
}

@Preview
@Composable
private fun WishlistItemsListPreview() {
    GiftingTheme {
        WishlistItemsList(
            items = listOf(
                WishlistItem(
                    wishlistId = "",
                    id = "",
                    name = "Item1",
                    description = "Item 1 description",
                    owner = ""
                ),
                WishlistItem(
                    wishlistId = "",
                    id = "",
                    name = "Item2",
                    categories = listOf(GiftCategory.Books),
                    owner = ""
                ),
                WishlistItem(
                    wishlistId = "",
                    id = "",
                    name = "Item3",
                    description = "Item 3 description",
                    price = GiftPrice.Exact(89.99),
                    categories = listOf(
                        GiftCategory.Books,
                        GiftCategory.Kids,
                        GiftCategory.Other
                    ),
                    owner = "",
                    notes = "This item is amazing, I need it now!"
                )
            ),
            onItemClick = { },
            onUrlClick = { },
            onItemLongClick = { }
        )
    }
}
