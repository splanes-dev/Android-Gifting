package com.splanes.gifting.data.feature.user.repository

import com.splanes.gifting.data.feature.user.datasource.UserLocalDataSource
import com.splanes.gifting.data.feature.user.mapper.UserLandingStateMapper
import com.splanes.gifting.domain.feature.user.model.AuthStateValue
import com.splanes.gifting.domain.feature.user.model.UserCredentials
import com.splanes.gifting.domain.feature.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val landingStateMapper: UserLandingStateMapper
) : UserRepository {

    override suspend fun updateLandingValue(value: AuthStateValue): Boolean =
        localDataSource.setLandingState(landingStateMapper.map(value))

    override suspend fun getLandingValue(): AuthStateValue =
        localDataSource.getLandingState().let(landingStateMapper::map)

    override suspend fun getCredentials(): UserCredentials? {
        TODO("Not yet implemented")
    }
}
