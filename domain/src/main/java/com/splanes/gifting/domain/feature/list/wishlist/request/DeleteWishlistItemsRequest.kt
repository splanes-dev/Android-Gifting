package com.splanes.gifting.domain.feature.list.wishlist.request

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem

data class DeleteWishlistItemsRequest(
    val wishlist: Wishlist,
    val itemsToDelete: List<WishlistItem>
)
