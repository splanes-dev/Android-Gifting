package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<Unit, AuthStateValue>() {

    override suspend fun execute(request: Unit): AuthStateValue {
        val authState = repository.getAuthStateValue()
        if (authState == AuthStateValue.OnBoarding) {
            repository.updateAuthStateValue(AuthStateValue.SignUp)
        }
        return authState
    }
}
