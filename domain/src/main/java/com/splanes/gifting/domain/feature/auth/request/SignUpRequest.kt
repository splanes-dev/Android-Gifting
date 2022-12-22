package com.splanes.gifting.domain.feature.auth.request

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String,
    val autoSignIn: Boolean
)
