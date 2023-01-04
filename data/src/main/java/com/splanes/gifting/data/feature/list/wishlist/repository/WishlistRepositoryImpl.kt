package com.splanes.gifting.data.feature.list.wishlist.repository

import com.splanes.gifting.data.common.uid.UidRepositoryImpl
import com.splanes.gifting.data.feature.list.wishlist.datasource.WishlistRemoteDataSource
import com.splanes.gifting.data.feature.list.wishlist.entity.mapper.WishlistMapper
import com.splanes.gifting.data.feature.tag.datasource.TagRemoteDataSource
import com.splanes.gifting.domain.common.error.GenericException
import com.splanes.gifting.domain.feature.list.wishlist.WishlistRepository
import com.splanes.gifting.domain.feature.list.wishlist.model.Wishlist
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WishlistRepositoryImpl @Inject constructor(
    private val wishlistDataSource: WishlistRemoteDataSource,
    private val tagDataSource: TagRemoteDataSource,
    private val wishlistMapper: WishlistMapper
) : WishlistRepository, UidRepositoryImpl() {

    override suspend fun getWishlists(): List<Wishlist> =
        withContext(Dispatchers.IO) {
            val wishlists = wishlistDataSource.getWishlists()
            val tags = tagDataSource.getTags()
            wishlists.map { wishlistDto -> wishlistMapper.map(wishlistDto, tags) }
        }

    override suspend fun createOrUpdateWishlist(wishlist: Wishlist): Wishlist =
        withContext(Dispatchers.IO) {
            val dto = wishlistMapper.map(wishlist)
            if (wishlistDataSource.createOrUpdateWishlist(dto)) {
                wishlist
            } else {
                throw GenericException
            }
        }

    override suspend fun observeWishlists(): Flow<List<Wishlist>> {
        TODO("Not yet implemented")
    }
}
