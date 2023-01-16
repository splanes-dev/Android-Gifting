package com.splanes.gifting.data.feature.profile.repository

import com.splanes.gifting.data.feature.auth.datasource.AuthLocalDataSource
import com.splanes.gifting.domain.feature.profile.ProfileRepository
import com.splanes.gifting.domain.feature.profile.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : ProfileRepository {

    override suspend fun getProfile(): Profile {
        val credentials = authLocalDataSource.getStoredCredentials()
        TODO()
    }
}
