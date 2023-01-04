package com.splanes.gifting.ui.feature.wishlists.model

import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.domain.feature.list.model.ItemTag

data class WishlistItemFormResultData(
    val name: String,
    val description: String?,
    val price: Double?,
    val url: String?,
    val categories: List<GiftCategory>,
    val notes: String?,
    val tags: List<ItemTag>
)
