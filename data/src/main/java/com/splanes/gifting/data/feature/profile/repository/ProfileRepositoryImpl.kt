package com.splanes.gifting.data.feature.profile.repository

import com.splanes.gifting.data.feature.profile.datasource.ProfileRemoteDataSource
import com.splanes.gifting.data.feature.profile.entity.mapper.ProfileMapper
import com.splanes.gifting.domain.feature.profile.ProfileRepository
import com.splanes.gifting.domain.feature.profile.model.Profile
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val profileMapper: ProfileMapper
) : ProfileRepository {

    override suspend fun getProfile(): Profile =
        withContext(Dispatchers.IO) {
            val dto = profileRemoteDataSource.getProfile()
            profileMapper.map(dto)
        }

    override suspend fun insertOrUpdateUsername(username: String): Boolean =
        withContext(Dispatchers.IO) {
            profileRemoteDataSource.insertOrUpdateUsername(username)
        }
}
