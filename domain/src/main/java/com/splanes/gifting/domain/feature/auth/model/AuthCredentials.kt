package com.splanes.gifting.domain.feature.auth.model

data class AuthCredentials(
    val username: String,
    val email: String,
    val password: String
)
