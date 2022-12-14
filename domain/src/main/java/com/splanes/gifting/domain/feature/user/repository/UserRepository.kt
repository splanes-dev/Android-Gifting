package com.splanes.gifting.domain.feature.user.repository

import com.splanes.gifting.domain.feature.user.model.UserCredentials
import com.splanes.gifting.domain.feature.user.model.UserLandingValue

interface UserRepository {

    suspend fun updateLandingValue(value: UserLandingValue): Boolean

    suspend fun getLandingValue(): UserLandingValue

    suspend fun getCredentials(): UserCredentials?
}
