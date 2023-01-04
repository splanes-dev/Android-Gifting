package com.splanes.gifting.domain.feature.list.wishlist.request

import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.domain.feature.list.model.ItemTag
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist

data class NewWishlistItemRequest(
    val wishlist: Wishlist,
    val name: String,
    val description: String?,
    val price: Double?,
    val url: String?,
    val notes: String?,
    val categories: List<GiftCategory>,
    val tags: List<ItemTag>
)
