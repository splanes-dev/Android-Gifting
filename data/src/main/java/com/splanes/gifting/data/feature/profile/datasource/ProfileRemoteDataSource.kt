package com.splanes.gifting.data.feature.profile.datasource

import com.splanes.gifting.data.feature.profile.entity.ProfileDto

interface ProfileRemoteDataSource {

    suspend fun getProfile(): ProfileDto

    suspend fun insertOrUpdateUsername(username: String): Boolean
}
