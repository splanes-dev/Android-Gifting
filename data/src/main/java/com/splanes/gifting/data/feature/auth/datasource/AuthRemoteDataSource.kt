package com.splanes.gifting.data.feature.auth.datasource

interface AuthRemoteDataSource {

    suspend fun signUp(email: String, password: String): String

    suspend fun signIn(email: String, password: String): String
}
