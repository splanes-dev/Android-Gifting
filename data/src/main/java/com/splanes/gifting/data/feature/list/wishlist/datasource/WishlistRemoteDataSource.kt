package com.splanes.gifting.data.feature.list.wishlist.datasource

import com.splanes.gifting.data.feature.list.wishlist.entity.WishlistDto

interface WishlistRemoteDataSource {

    suspend fun getWishlists(): List<WishlistDto>
}
