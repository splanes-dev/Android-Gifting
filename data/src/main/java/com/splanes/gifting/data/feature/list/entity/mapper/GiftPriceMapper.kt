package com.splanes.gifting.data.feature.list.entity.mapper

import com.splanes.gifting.data.feature.list.entity.GiftPriceDto
import com.splanes.gifting.domain.feature.list.model.GiftPrice
import javax.inject.Inject

class GiftPriceMapper @Inject constructor() {

    fun map(dto: GiftPriceDto): GiftPrice = if (dto.isPriceApprox) {
        GiftPrice.Approx(dto.value)
    } else {
        GiftPrice.Exact(dto.value)
    }

    fun map(price: GiftPrice): GiftPriceDto =
        GiftPriceDto(
            value = price.value,
            isPriceApprox = price is GiftPrice.Approx
        )
}
