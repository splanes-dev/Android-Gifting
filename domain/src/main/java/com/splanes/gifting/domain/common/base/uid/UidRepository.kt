package com.splanes.gifting.domain.common.base.uid

interface UidRepository {
    suspend fun getUid(): String
}
