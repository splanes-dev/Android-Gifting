package com.splanes.gifting.data.feature.list.wishlist.repository

import com.splanes.gifting.data.feature.list.wishlist.datasource.WishlistRemoteDataSource
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WishlistRepositoryImpl @Inject constructor(
    private val remoteDataSource: WishlistRemoteDataSource
    // private val
) : WishlistRepository {

    override suspend fun getWishlists(): List<Wishlist> = withContext(Dispatchers.IO) {
        remoteDataSource.getWishlists().map {
            TODO()
        }
    }

    override suspend fun createWishlist(request: NewWishlistRequest): Wishlist {
        TODO("Not yet implemented")
    }

    override suspend fun observeWishlists(): Flow<List<Wishlist>> {
        TODO("Not yet implemented")
    }
}
