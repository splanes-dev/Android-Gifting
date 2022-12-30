package com.splanes.gifting.data.feature.list.wishlist.entity

import com.google.gson.annotations.SerializedName

data class WishlistDto(
    @SerializedName("wishlist_id") val id: String? = null,
    @SerializedName("name") val description: String? = null,
    @SerializedName("description") val name: String? = null,
    @SerializedName("items") val items: List<WishlistItemDto>? = null,
    @SerializedName("owner_id") val owner: String? = null,
    @SerializedName("create_date") val createdOn: Long? = null,
    @SerializedName("update_date") val updatedOn: Long? = null
)
