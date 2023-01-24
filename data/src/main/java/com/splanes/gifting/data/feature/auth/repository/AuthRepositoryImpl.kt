package com.splanes.gifting.data.feature.auth.repository

import com.splanes.gifting.data.feature.auth.datasource.AuthLocalDataSource
import com.splanes.gifting.data.feature.auth.datasource.AuthRemoteDataSource
import com.splanes.gifting.data.feature.auth.entity.mapper.AuthCredentialsMapper
import com.splanes.gifting.data.feature.auth.entity.mapper.AuthStateValueMapper
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignInRequest
import com.splanes.gifting.domain.feature.auth.request.SignUpRequest
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val authStateValueMapper: AuthStateValueMapper,
    private val authCredentialsMapper: AuthCredentialsMapper
) : AuthRepository {

    override suspend fun getAuthStateValue(): AuthStateValue = withContext(Dispatchers.IO) {
        authLocalDataSource.getAuthStateValue().let(authStateValueMapper::map)
    }

    override suspend fun updateAuthStateValue(authStateValue: AuthStateValue): Boolean =
        withContext(Dispatchers.IO) {
            authLocalDataSource.updateAuthStateValue(authStateValue.let(authStateValueMapper::map))
        }

    override suspend fun signUp(signUpRequest: SignUpRequest): String =
        withContext(Dispatchers.IO) {
            authRemoteDataSource.signUp(signUpRequest.email, signUpRequest.password)
        }

    override suspend fun updateUserProfile(username: String): Boolean =
        withContext(Dispatchers.IO) {
            authRemoteDataSource.updateUsername(username)
        }

    override suspend fun updatePassword(password: String): Boolean =
        withContext(Dispatchers.IO) {
            authRemoteDataSource.updatePassword(password)
        }

    override suspend fun signIn(signInRequest: SignInRequest): String =
        withContext(Dispatchers.IO) {
            authRemoteDataSource.signIn(signInRequest.email, signInRequest.password)
        }

    override suspend fun storeCredentials(credentials: AuthCredentials): Boolean =
        withContext(Dispatchers.IO) {
            authLocalDataSource.storeCredentials(credentials.let(authCredentialsMapper::map))
        }

    override suspend fun getStoredCredentials(): AuthCredentials? =
        withContext(Dispatchers.IO) {
            authLocalDataSource.getStoredCredentials()?.let(authCredentialsMapper::map)
        }
}
