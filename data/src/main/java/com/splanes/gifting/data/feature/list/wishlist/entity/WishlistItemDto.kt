package com.splanes.gifting.data.feature.list.wishlist.entity

import com.google.gson.annotations.SerializedName

data class WishlistItemDto(
    @SerializedName("wishlist_id") val wishlistId: String? = null,
    @SerializedName("wishlist_item_id") val id: String? = null,
    @SerializedName("wishlist_item_owner_id") val owner: String? = null,
    @SerializedName("wishlist_item_name") val name: String? = null,
    @SerializedName("wishlist_item_description") val description: String? = null,
    @SerializedName("wishlist_item_creation_date") val createdOn: Long? = null,
    @SerializedName("wishlist_item_price") val price: Double? = null,
    @SerializedName("wishlist_item_url") val url: String? = null,
    @SerializedName("wishlist_item_tags") val tags: List<String>? = null,
    @SerializedName("wishlist_item_notes") val notes: String? = null,
    @SerializedName("wishlist_item_categories") val categories: List<String>? = null
)
