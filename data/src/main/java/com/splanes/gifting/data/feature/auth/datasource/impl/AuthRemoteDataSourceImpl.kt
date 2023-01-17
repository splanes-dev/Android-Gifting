package com.splanes.gifting.data.feature.auth.datasource.impl

import com.google.firebase.auth.FirebaseAuth
import com.splanes.gifting.data.common.database.GiftingRemoteDatabase
import com.splanes.gifting.data.common.utils.task.awaitIsSuccessful
import com.splanes.gifting.data.common.utils.task.awaitOrThrow
import com.splanes.gifting.data.feature.auth.datasource.AuthRemoteDataSource
import com.splanes.gifting.domain.common.error.SignInException
import com.splanes.gifting.domain.common.error.SignUpException
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: GiftingRemoteDatabase
) : AuthRemoteDataSource {

    override suspend fun signUp(email: String, password: String): String {
        val result = firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .awaitOrThrow(SignUpException)
        return result.user?.uid ?: throw SignUpException
    }

    override suspend fun signIn(email: String, password: String): String {
        val result = firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .awaitOrThrow(SignInException)
        return result.user?.uid ?: throw SignInException
    }

    override suspend fun storeUser(emailHash: String, username: String): Boolean =
        database
            .usersRef
            .child(emailHash)
            .setValue(username)
            .awaitIsSuccessful()
}
