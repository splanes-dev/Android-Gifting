package com.splanes.gifting.data.feature.auth.repository

import com.splanes.gifting.data.feature.auth.datasource.AuthLocalDataSource
import com.splanes.gifting.data.feature.auth.entity.mapper.AuthStateValueMapper
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val authStateValueMapper: AuthStateValueMapper
) : AuthRepository {

    override suspend fun getAuthStateValue(): AuthStateValue = withContext(Dispatchers.IO) {
        authLocalDataSource.getAuthStateValue().let(authStateValueMapper::map)
    }

    override suspend fun updateAuthStateValue(authStateValue: AuthStateValue): Boolean =
        withContext(Dispatchers.IO) {
            authLocalDataSource.updateAuthStateValue(authStateValue.let(authStateValueMapper::map))
        }
}
