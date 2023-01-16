package com.splanes.gifting.ui.feature.authentication.model

data class SignUpFormData(
    val username: String,
    val email: String,
    val password: String,
    val autoSignIn: Boolean
)
