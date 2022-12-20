package com.splanes.gifting.domain.feature.user.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.base.usecase.UseCaseScope
import com.splanes.gifting.domain.feature.user.model.AuthStateValue
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor() : UseCase<Unit, AuthStateValue>() {

    override suspend fun UseCaseScope<AuthStateValue>.execute(request: Unit) {
        TODO("Not yet implemented")
    }
}
