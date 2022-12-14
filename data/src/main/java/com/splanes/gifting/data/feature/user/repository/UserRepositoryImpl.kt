package com.splanes.gifting.data.feature.user.repository

import com.splanes.gifting.data.feature.user.datasource.UserLocalDataSource
import com.splanes.gifting.data.feature.user.mapper.UserLandingStateMapper
import com.splanes.gifting.domain.feature.user.model.UserCredentials
import com.splanes.gifting.domain.feature.user.model.UserLandingValue
import com.splanes.gifting.domain.feature.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val landingStateMapper: UserLandingStateMapper
) : UserRepository {

    override suspend fun updateLandingValue(value: UserLandingValue): Boolean =
        localDataSource.setLandingState(landingStateMapper.map(value))

    override suspend fun getLandingValue(): UserLandingValue =
        localDataSource.getLandingState().let(landingStateMapper::map)

    override suspend fun getCredentials(): UserCredentials? {
        TODO("Not yet implemented")
    }
}
