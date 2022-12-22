package com.splanes.gifting.data.feature.auth.datasource

import com.splanes.gifting.data.feature.auth.entity.AuthCredentialsDto
import com.splanes.gifting.data.feature.auth.entity.AuthStateValueDto

interface AuthLocalDataSource {

    suspend fun getAuthStateValue(): AuthStateValueDto?

    suspend fun updateAuthStateValue(dto: AuthStateValueDto): Boolean

    suspend fun getStoredCredentials(): AuthCredentialsDto?

    suspend fun storeCredentials(dto: AuthCredentialsDto): Boolean
}
