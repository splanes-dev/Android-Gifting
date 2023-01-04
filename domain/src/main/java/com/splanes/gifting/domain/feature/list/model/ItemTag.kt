package com.splanes.gifting.domain.feature.list.model

import com.splanes.gifting.domain.common.utils.uuid

data class ItemTag(
    val id: String = uuid(),
    val name: String
)
