package com.splanes.gifting.data.feature.user.datasource

import android.content.SharedPreferences
import com.splanes.gifting.data.common.utils.preferences.getJson
import com.splanes.gifting.data.common.utils.preferences.putJson
import com.splanes.gifting.data.common.utils.preferences.read
import com.splanes.gifting.data.common.utils.preferences.write
import com.splanes.gifting.data.feature.user.entity.UserLandingValueDto
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) : UserLocalDataSource {

    override suspend fun setLandingState(value: UserLandingValueDto) =
        preferences.write { putJson(LandingStateKey, value) }

    override suspend fun getLandingState(): UserLandingValueDto? =
        preferences.read { getJson(LandingStateKey) }
}

private const val LandingStateKey = "gifting.user.LandingState"
