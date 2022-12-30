package com.splanes.gifting.data.feature.tag.datasource.impl

import com.splanes.gifting.data.common.database.GiftingRemoteDatabase
import com.splanes.gifting.data.common.utils.database.get
import com.splanes.gifting.data.common.utils.database.read
import com.splanes.gifting.data.feature.tag.datasource.TagRemoteDataSource
import com.splanes.gifting.data.feature.tag.entity.ItemTagDto
import javax.inject.Inject

class TagRemoteDataSourceImpl @Inject constructor(
    private val database: GiftingRemoteDatabase
) : TagRemoteDataSource {

    override suspend fun getTags(): List<ItemTagDto> =
        database
            .tagsRef
            .read { snapshot ->
                snapshot.children.mapNotNull { it.get() }
            }
}
