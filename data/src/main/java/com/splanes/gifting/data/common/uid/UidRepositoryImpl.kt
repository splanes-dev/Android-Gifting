package com.splanes.gifting.data.common.uid

import com.google.firebase.auth.FirebaseAuth
import com.splanes.gifting.domain.common.base.uid.UidRepository
import javax.inject.Inject

abstract class UidRepositoryImpl : UidRepository {

    @Inject
    lateinit var auth: FirebaseAuth

    override suspend fun getUid(): String = auth.currentUser?.uid.orEmpty()
}
