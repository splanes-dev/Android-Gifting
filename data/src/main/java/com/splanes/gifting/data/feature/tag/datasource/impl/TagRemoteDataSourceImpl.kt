package com.splanes.gifting.data.feature.tag.datasource.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.splanes.gifting.data.feature.tag.datasource.TagRemoteDataSource
import com.splanes.gifting.data.feature.tag.entity.GiftTagDto
import javax.inject.Inject

class TagRemoteDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) : TagRemoteDataSource {

    override suspend fun getTags(): List<GiftTagDto> {
        TODO("Not yet implemented")
    }
}
