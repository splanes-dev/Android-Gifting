package com.splanes.gifting.data.feature.tag.entity.mapper

import com.splanes.gifting.data.feature.tag.entity.ItemTagDto
import com.splanes.gifting.domain.feature.list.model.ItemTag
import javax.inject.Inject

class ItemTagMapper @Inject constructor() {

    fun map(dto: ItemTagDto): ItemTag =
        ItemTag(
            id = dto.id.orEmpty(),
            name = dto.name.orEmpty()
        )

    fun map(tag: ItemTag): ItemTagDto =
        ItemTagDto(
            id = tag.id,
            name = tag.name
        )
}
