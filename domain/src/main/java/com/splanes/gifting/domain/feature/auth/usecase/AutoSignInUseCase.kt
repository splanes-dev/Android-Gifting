package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.error.SignInException
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.request.SignInRequest
import javax.inject.Inject

class AutoSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(request: Unit) {
        val credentials = repository.getStoredCredentials()
        if (credentials != null) {
            repository.signIn(
                SignInRequest(
                    email = credentials.email,
                    password = credentials.password,
                    autoSignIn = true
                )
            )
        } else {
            throw SignInException
        }
    }
}
