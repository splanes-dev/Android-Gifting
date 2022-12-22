package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignInRequest
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<SignInRequest, Unit>() {

    override suspend fun execute(request: SignInRequest) {
        val uid = repository.signIn(request)
        if (request.autoSignIn) {
            val authState = repository.getAuthStateValue()
            if (authState != AuthStateValue.AutoSignIn) {
                val storedSuccess = repository.storeCredentials(
                    AuthCredentials(
                        uid = uid,
                        email = request.email,
                        password = request.password
                    )
                )
                if (storedSuccess) {
                    repository.updateAuthStateValue(AuthStateValue.AutoSignIn)
                }
            }
        }
    }
}
