package com.splanes.gifting.data.feature.auth.datasource.impl

import android.content.SharedPreferences
import com.splanes.gifting.data.common.utils.preferences.getJson
import com.splanes.gifting.data.common.utils.preferences.putJson
import com.splanes.gifting.data.common.utils.preferences.read
import com.splanes.gifting.data.common.utils.preferences.write
import com.splanes.gifting.data.feature.auth.datasource.AuthLocalDataSource
import com.splanes.gifting.data.feature.auth.entity.AuthCredentialsDto
import com.splanes.gifting.data.feature.auth.entity.AuthStateValueDto
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) : AuthLocalDataSource {

    override suspend fun getAuthStateValue(): AuthStateValueDto? =
        preferences.read { getJson(AuthStateValueKey) }

    override suspend fun updateAuthStateValue(dto: AuthStateValueDto): Boolean =
        preferences.write { putJson(AuthStateValueKey, dto) }

    override suspend fun getStoredCredentials(): AuthCredentialsDto? =
        preferences.read { getJson(AuthCredentialsKey) }

    override suspend fun storeCredentials(dto: AuthCredentialsDto): Boolean =
        preferences.write { putJson(AuthCredentialsKey, dto) }
}

private const val AuthStateValueKey = "gifting.auth.AuthStateValue"
private const val AuthCredentialsKey = "gifting.auth.AuthCredentials"
