package com.splanes.gifting.domain.feature.profile

import com.splanes.gifting.domain.feature.profile.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile

    suspend fun insertOrUpdateUsername(username: String): Boolean
}
