package com.splanes.gifting.data.feature.list.wishlist.datasource.impl

import com.splanes.gifting.data.common.database.GiftingRemoteDatabase
import com.splanes.gifting.data.common.utils.database.get
import com.splanes.gifting.data.common.utils.database.read
import com.splanes.gifting.data.common.utils.database.write
import com.splanes.gifting.data.common.utils.task.awaitIsSuccessful
import com.splanes.gifting.data.feature.list.wishlist.datasource.WishlistRemoteDataSource
import com.splanes.gifting.data.feature.list.wishlist.entity.WishlistDto
import javax.inject.Inject

class WishlistRemoteDataSourceImpl @Inject constructor(
    private val database: GiftingRemoteDatabase
) : WishlistRemoteDataSource {

    override suspend fun getWishlists(): List<WishlistDto> =
        database
            .wishlistsRef
            .read { snapshot ->
                snapshot.children.mapNotNull {
                    it.get()
                }
            }

    override suspend fun createOrUpdateWishlist(wishlist: WishlistDto): Boolean =
        database
            .wishlistsRef
            .child(wishlist.id.orEmpty())
            .write(wishlist)

    override suspend fun deleteWishlist(id: String): Boolean =
        database
            .wishlistsRef
            .child(id)
            .removeValue()
            .awaitIsSuccessful()
}
