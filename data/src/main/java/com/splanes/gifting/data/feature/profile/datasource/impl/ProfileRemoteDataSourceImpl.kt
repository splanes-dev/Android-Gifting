package com.splanes.gifting.data.feature.profile.datasource.impl

import com.google.firebase.auth.FirebaseAuth
import com.splanes.gifting.data.common.database.GiftingRemoteDatabase
import com.splanes.gifting.data.common.utils.database.write
import com.splanes.gifting.data.common.utils.hash.md5
import com.splanes.gifting.data.common.utils.user.userOrThrow
import com.splanes.gifting.data.feature.profile.datasource.ProfileRemoteDataSource
import com.splanes.gifting.data.feature.profile.entity.ProfileDto
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: GiftingRemoteDatabase
) : ProfileRemoteDataSource {

    override suspend fun getProfile(): ProfileDto =
        with(firebaseAuth.userOrThrow()) {
            ProfileDto(
                name = displayName,
                email = email
            )
        }

    override suspend fun insertOrUpdateUsername(username: String): Boolean =
        with(firebaseAuth.userOrThrow()) {
            database
                .usersRef
                .child(email.orEmpty().md5())
                .write(username)
        }
}
