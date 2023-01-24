package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.error.SignUpException
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignUpRequest
import com.splanes.gifting.domain.feature.profile.ProfileRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) : UseCase<SignUpRequest, Unit>() {

    override suspend fun execute(request: SignUpRequest) {
        val uid = authRepository.signUp(request)
        val isUserProfileUpdated = authRepository.updateUserProfile(request.username)
        val isUsernameStored = profileRepository.insertOrUpdateUsername(request.username)

        if (!isUserProfileUpdated || !isUsernameStored) {
            throw SignUpException
        }

        if (request.autoSignIn) {
            val storeSuccess = authRepository.storeCredentials(
                AuthCredentials(
                    uid = uid,
                    email = request.email,
                    password = request.password
                )
            )
            authRepository.updateAuthStateValue(
                if (storeSuccess) {
                    AuthStateValue.AutoSignIn
                } else {
                    AuthStateValue.SignIn
                }
            )
        } else {
            authRepository.updateAuthStateValue(AuthStateValue.SignIn)
        }
    }
}
