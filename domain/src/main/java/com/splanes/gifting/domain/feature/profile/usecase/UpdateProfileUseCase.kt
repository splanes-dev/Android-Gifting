package com.splanes.gifting.domain.feature.profile.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.error.GenericException
import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.profile.ProfileRepository
import com.splanes.gifting.domain.feature.profile.model.Profile
import com.splanes.gifting.domain.feature.profile.request.UpdateProfileRequest
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) : UseCase<UpdateProfileRequest, Profile>() {

    override suspend fun execute(request: UpdateProfileRequest): Profile {
        val profile = profileRepository.getProfile()
        if (profile.name != request.username) {
            val isUserProfileUpdated = authRepository.updateUserProfile(request.username)
            val isUsernameUpdated = profileRepository.insertOrUpdateUsername(request.username)
            if (!isUserProfileUpdated || !isUsernameUpdated) {
                throw GenericException
            }
        }
        if (request.password?.isNotBlank() == true) {
            val isPasswordUpdated = authRepository.updatePassword(request.password)
            if (isPasswordUpdated) {
                val storedCredentials = authRepository.getStoredCredentials()
                if (storedCredentials != null) {
                    val isStoredCredentialsOk = authRepository.storeCredentials(
                        storedCredentials.copy(password = request.password)
                    )
                    if (!isStoredCredentialsOk) {
                        throw GenericException
                    }
                }
            } else {
                throw GenericException
            }
        }
        return profileRepository.getProfile()
    }
}
