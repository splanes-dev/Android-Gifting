package com.splanes.gifting.domain.feature.list.wishlist.model

import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.domain.feature.list.model.GiftItem
import com.splanes.gifting.domain.feature.list.model.ItemTag

data class WishlistItem(
    val wishlistId: String,
    override val id: String,
    override val owner: String,
    override val name: String,
    override val addedOn: Long = System.currentTimeMillis(),
    override val description: String? = null,
    override val price: Double? = null,
    override val url: String? = null,
    override val tags: List<ItemTag> = emptyList(),
    override val notes: String? = null,
    override val categories: List<GiftCategory> = emptyList()
) : GiftItem
