package com.splanes.gifting.data.feature.user.datasource

import com.splanes.gifting.data.feature.user.entity.UserLandingValueDto

interface UserLocalDataSource {

    suspend fun setLandingState(value: UserLandingValueDto): Boolean

    suspend fun getLandingState(): UserLandingValueDto?
}
