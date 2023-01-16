package com.splanes.gifting.ui.feature.authentication.model

data class SignInFormData(
    val email: String,
    val password: String,
    val autoSignIn: Boolean
)
