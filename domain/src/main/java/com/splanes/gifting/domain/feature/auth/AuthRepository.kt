package com.splanes.gifting.domain.feature.auth

import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignInRequest
import com.splanes.gifting.domain.feature.auth.request.SignUpRequest

interface AuthRepository {
    suspend fun getAuthStateValue(): AuthStateValue

    suspend fun updateAuthStateValue(authStateValue: AuthStateValue): Boolean

    suspend fun signIn(signInRequest: SignInRequest): String

    suspend fun signUp(signUpRequest: SignUpRequest): String

    suspend fun updateUserProfile(username: String): Boolean

    suspend fun updatePassword(password: String): Boolean

    suspend fun storeCredentials(credentials: AuthCredentials): Boolean

    suspend fun getStoredCredentials(): AuthCredentials?
}
