package com.splanes.gifting.data.feature.list.wishlist.entity

import com.google.gson.annotations.SerializedName

data class WishlistItemDto(
    @SerializedName("wishlist_id") val wishlistId: String?,
    @SerializedName("wishlist_item_id") val id: String?,
    @SerializedName("wishlist_item_owner_id") val owner: String?,
    @SerializedName("wishlist_item_name") val name: String?,
    @SerializedName("wishlist_item_description") val description: String?,
    @SerializedName("wishlist_item_creation_date") val createdOn: Long?,
    @SerializedName("wishlist_item_price") val price: Double?,
    @SerializedName("wishlist_item_url") val url: String?,
    @SerializedName("wishlist_item_tags") val tags: List<String>?,
    @SerializedName("wishlist_item_notes") val notes: String?,
    @SerializedName("wishlist_item_categories") val categories: List<String>?
)
