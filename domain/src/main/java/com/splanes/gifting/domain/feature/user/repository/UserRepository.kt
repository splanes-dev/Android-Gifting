package com.splanes.gifting.domain.feature.user.repository

import com.splanes.gifting.domain.feature.user.model.AuthStateValue
import com.splanes.gifting.domain.feature.user.model.UserCredentials

interface UserRepository {

    suspend fun updateLandingValue(value: AuthStateValue): Boolean

    suspend fun getLandingValue(): AuthStateValue

    suspend fun getCredentials(): UserCredentials?
}
