package com.splanes.gifting.data.feature.auth.entity.mapper

import com.splanes.gifting.data.feature.auth.entity.AuthCredentialsDto
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import javax.inject.Inject

class AuthCredentialsMapper @Inject constructor() {

    fun map(dto: AuthCredentialsDto): AuthCredentials = with(dto) {
        AuthCredentials(
            uid = uid.orEmpty(),
            email = email.orEmpty(),
            password = password.orEmpty()
        )
    }

    fun map(value: AuthCredentials): AuthCredentialsDto = with(value) {
        AuthCredentialsDto(
            uid = uid,
            email = email,
            password = password
        )
    }
}
