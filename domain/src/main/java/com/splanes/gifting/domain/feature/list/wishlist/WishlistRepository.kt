package com.splanes.gifting.domain.feature.list.wishlist

import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    suspend fun getWishlists(): List<Wishlist>
    suspend fun createWishlist(request: NewWishlistRequest): Wishlist
    suspend fun observeWishlists(): Flow<List<Wishlist>>
}
