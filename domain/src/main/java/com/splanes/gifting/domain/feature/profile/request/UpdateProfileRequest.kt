package com.splanes.gifting.domain.feature.profile.request

data class UpdateProfileRequest(
    val username: String,
    val password: String?
)
