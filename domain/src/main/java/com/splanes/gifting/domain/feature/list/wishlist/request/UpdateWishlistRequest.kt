package com.splanes.gifting.domain.feature.list.wishlist.request

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist

data class UpdateWishlistRequest(
    val wishlist: Wishlist,
    val name: String,
    val description: String?
)
