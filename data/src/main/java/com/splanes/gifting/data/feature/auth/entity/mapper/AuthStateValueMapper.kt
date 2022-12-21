package com.splanes.gifting.data.feature.auth.entity.mapper

import com.splanes.gifting.data.feature.auth.entity.AuthStateValueDto
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import javax.inject.Inject

class AuthStateValueMapper @Inject constructor() {

    fun map(dto: AuthStateValueDto?): AuthStateValue = when (dto) {
        AuthStateValueDto.SignUp -> AuthStateValue.SignUp
        AuthStateValueDto.SignIn -> AuthStateValue.SignIn
        AuthStateValueDto.AutoSignIn -> AuthStateValue.AutoSignIn
        else -> AuthStateValue.OnBoarding
    }

    fun map(value: AuthStateValue): AuthStateValueDto = when (value) {
        AuthStateValue.OnBoarding -> AuthStateValueDto.OnBoarding
        AuthStateValue.SignUp -> AuthStateValueDto.SignUp
        AuthStateValue.AutoSignIn -> AuthStateValueDto.AutoSignIn
        AuthStateValue.SignIn -> AuthStateValueDto.SignIn
    }
}
