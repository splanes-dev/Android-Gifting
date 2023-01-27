package com.splanes.gifting.domain.feature.list.model

sealed class GiftStatus {
    object Available : GiftStatus()

    data class Reserved(
        val owner: String,
        val reservedOn: Long
    ) : GiftStatus()

    data class Bought(
        val owner: String,
        val boughtOn: Long
    ) : GiftStatus()
}
