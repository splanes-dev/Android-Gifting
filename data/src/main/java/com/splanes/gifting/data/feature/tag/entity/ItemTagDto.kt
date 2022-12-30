package com.splanes.gifting.data.feature.tag.entity

import com.google.gson.annotations.SerializedName

data class ItemTagDto(
    @SerializedName("gift_tag_id") val id: String?,
    @SerializedName("gift_tag_name") val name: String?
)
