package com.splanes.gifting.data.common.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.splanes.gifting.domain.common.error.NotLoggedException
import javax.inject.Inject

class GiftingRemoteDatabase @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) {

    private val uid
        get() = auth.currentUser?.uid ?: throw NotLoggedException

    val wishlistsRef
        get() = database.reference
            .child(uid)
            .child("wishlists")

    val tagsRef
        get() = database.reference
            .child(uid)
            .child("tags")

    val usersRef
        get() = database.reference
            .child("usr")
}
