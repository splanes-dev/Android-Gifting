package com.splanes.gifting.data.feature.list.entity

import com.google.gson.annotations.SerializedName

data class GiftPriceDto(
    @SerializedName("price_value") val value: Double,
    @SerializedName("price_approx") val isPriceApprox: Boolean
)
