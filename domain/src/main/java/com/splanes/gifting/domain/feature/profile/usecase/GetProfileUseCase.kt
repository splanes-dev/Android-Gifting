package com.splanes.gifting.domain.feature.profile.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.feature.profile.ProfileRepository
import com.splanes.gifting.domain.feature.profile.model.Profile
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) : UseCase<Unit, Profile>() {

    override suspend fun execute(request: Unit): Profile =
        repository.getProfile()
}
