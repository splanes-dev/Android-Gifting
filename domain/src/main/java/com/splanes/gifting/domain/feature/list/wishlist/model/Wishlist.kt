package com.splanes.gifting.domain.feature.list.wishlist.model

data class Wishlist(
    val id: String,
    val name: String,
    val description: String? = null,
    val items: List<WishlistItem> = emptyList(),
    val owner: String,
    val createdOn: Long,
    val updatedOn: Long
)
