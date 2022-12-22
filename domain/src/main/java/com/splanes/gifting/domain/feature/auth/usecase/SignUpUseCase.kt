package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignUpRequest
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<SignUpRequest, Unit>() {

    override suspend fun execute(request: SignUpRequest) {
        val uid = repository.signUp(request)
        if (request.autoSignIn) {
            val storeSuccess = repository.storeCredentials(
                AuthCredentials(
                    uid = uid,
                    email = request.email,
                    password = request.password
                )
            )
            repository.updateAuthStateValue(
                if (storeSuccess) {
                    AuthStateValue.AutoSignIn
                } else {
                    AuthStateValue.SignIn
                }
            )
        }
    }
}
