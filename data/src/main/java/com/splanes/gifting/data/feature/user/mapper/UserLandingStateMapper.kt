package com.splanes.gifting.data.feature.user.mapper

import com.splanes.gifting.data.feature.user.entity.UserLandingValueDto
import com.splanes.gifting.domain.feature.user.model.AuthStateValue
import javax.inject.Inject

class UserLandingStateMapper @Inject constructor() {

    fun map(value: AuthStateValue) = when (value) {
        AuthStateValue.OnBoarding -> UserLandingValueDto.OnBoarding
        AuthStateValue.AutoSignIn -> UserLandingValueDto.AutoSignIn
        AuthStateValue.SignIn -> UserLandingValueDto.SignIn
        AuthStateValue.SignUp -> UserLandingValueDto.SignUp
    }

    fun map(value: UserLandingValueDto?) = when (value) {
        UserLandingValueDto.AutoSignIn -> AuthStateValue.AutoSignIn
        UserLandingValueDto.SignIn -> AuthStateValue.SignIn
        UserLandingValueDto.SignUp -> AuthStateValue.SignUp
        UserLandingValueDto.OnBoarding,
        null -> AuthStateValue.OnBoarding
    }
}
