package com.splanes.gifting.domain.feature.auth.request

data class SignInRequest(
    val email: String,
    val password: String,
    val autoSignIn: Boolean
)
