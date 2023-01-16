package com.splanes.gifting.data.feature.profile.entity

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("username") val name: String? = null,
    @SerializedName("email") val email: String? = null
)
