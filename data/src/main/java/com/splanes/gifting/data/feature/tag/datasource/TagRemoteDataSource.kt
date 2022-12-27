package com.splanes.gifting.data.feature.tag.datasource

import com.splanes.gifting.data.feature.tag.entity.GiftTagDto

interface TagRemoteDataSource {
    suspend fun getTags(): List<GiftTagDto>
}
