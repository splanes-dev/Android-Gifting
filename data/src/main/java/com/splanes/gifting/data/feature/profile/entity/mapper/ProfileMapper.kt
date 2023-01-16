package com.splanes.gifting.data.feature.profile.entity.mapper

import com.splanes.gifting.data.common.utils.hash.md5
import com.splanes.gifting.data.common.utils.profile.GravatarConstants
import com.splanes.gifting.data.feature.profile.entity.ProfileDto
import com.splanes.gifting.domain.feature.profile.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun map(dto: ProfileDto): Profile =
        Profile(
            name = dto.name.orEmpty(),
            email = dto.email.orEmpty(),
            avatarUrl = dto.email?.let(::buildAvatarUrl).orEmpty()
        )

    fun map(model: Profile): ProfileDto =
        ProfileDto(
            name = model.name,
            email = model.email
        )

    private fun buildAvatarUrl(email: String): String =
        "${GravatarConstants.Url}/${email.md5()}?${GravatarConstants.QueryParams}"
}
