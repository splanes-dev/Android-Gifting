package com.splanes.gifting.domain.feature.list.wishlist

import com.splanes.gifting.domain.common.base.uid.UidRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import kotlinx.coroutines.flow.Flow

interface WishlistRepository : UidRepository {
    suspend fun getWishlists(): List<Wishlist>
    suspend fun createWishlist(wishlist: Wishlist): Wishlist
    suspend fun observeWishlists(): Flow<List<Wishlist>>
}
