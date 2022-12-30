package com.splanes.gifting.data.feature.tag.datasource

import com.splanes.gifting.data.feature.tag.entity.ItemTagDto

interface TagRemoteDataSource {
    suspend fun getTags(): List<ItemTagDto>
}
