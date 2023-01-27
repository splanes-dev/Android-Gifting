package com.splanes.gifting.domain.feature.list.model

import java.text.DecimalFormat

sealed interface GiftPrice {

    val value: Double

    val formatted: String
        get() = DecimalFormat("0.##€").format(value)

    @JvmInline
    value class Approx(override val value: Double) : GiftPrice

    @JvmInline
    value class Exact(override val value: Double) : GiftPrice
}
