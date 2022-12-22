package com.splanes.gifting.data.feature.auth.entity

import com.google.gson.annotations.SerializedName

data class AuthCredentialsDto(
    @SerializedName("user_id") val uid: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?
)
