package com.splanes.gifting.data.feature.list.wishlist.datasource.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.splanes.gifting.data.common.utils.database.async
import com.splanes.gifting.data.feature.list.wishlist.datasource.WishlistRemoteDataSource
import com.splanes.gifting.data.feature.list.wishlist.entity.WishlistDto
import com.splanes.gifting.domain.common.error.NotLoggedException
import javax.inject.Inject

class WishlistRemoteDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : WishlistRemoteDataSource {

    override suspend fun getWishlists(): List<WishlistDto> =
        database
            .wishlistRef(auth)
            .async { snapshot ->
                snapshot.children.mapNotNull {
                    it.getValue(WishlistDto::class.java)
                }
            }

    private fun FirebaseDatabase.wishlistRef(auth: FirebaseAuth) =
        auth.currentUser?.uid?.let { uid ->
            reference.child(uid).child("wishlists")
        } ?: throw NotLoggedException
}
