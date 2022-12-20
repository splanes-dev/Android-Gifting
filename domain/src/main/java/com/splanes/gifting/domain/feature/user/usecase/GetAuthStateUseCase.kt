package com.splanes.gifting.domain.feature.user.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.user.model.AuthStateValue
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor() : UseCase<Unit, AuthStateValue>() {

    override suspend fun execute(request: Unit): AuthStateValue {
        TODO("Not yet implemented")
    }
}
