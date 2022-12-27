package com.splanes.gifting.data.feature.list.wishlist.entity

import com.google.gson.annotations.SerializedName

data class WishlistDto(
    @SerializedName("wishlist_id") val id: String?,
    @SerializedName("name") val description: String?,
    @SerializedName("description") val name: String?,
    @SerializedName("items") val items: List<WishlistItemDto>?,
    @SerializedName("owner_id") val owner: String?,
    @SerializedName("create_date") val createdOn: Long?,
    @SerializedName("update_date") val updatedOn: Long?
)
