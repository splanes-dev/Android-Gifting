package com.splanes.gifting.domain.feature.auth

import com.splanes.gifting.domain.feature.auth.model.AuthStateValue

interface AuthRepository {
    suspend fun getAuthStateValue(): AuthStateValue
    suspend fun updateAuthStateValue(authStateValue: AuthStateValue): Boolean
}
