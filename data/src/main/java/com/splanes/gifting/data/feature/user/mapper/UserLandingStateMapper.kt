package com.splanes.gifting.data.feature.user.mapper

import com.splanes.gifting.data.feature.user.entity.UserLandingValueDto
import com.splanes.gifting.domain.feature.user.model.UserLandingValue
import javax.inject.Inject

class UserLandingStateMapper @Inject constructor() {

    fun map(value: UserLandingValue) = when (value) {
        UserLandingValue.OnBoarding -> UserLandingValueDto.OnBoarding
        UserLandingValue.AutoSignIn -> UserLandingValueDto.AutoSignIn
        UserLandingValue.SignIn -> UserLandingValueDto.SignIn
        UserLandingValue.SignUp -> UserLandingValueDto.SignUp
    }

    fun map(value: UserLandingValueDto?) = when (value) {
        UserLandingValueDto.AutoSignIn -> UserLandingValue.AutoSignIn
        UserLandingValueDto.SignIn -> UserLandingValue.SignIn
        UserLandingValueDto.SignUp -> UserLandingValue.SignUp
        UserLandingValueDto.OnBoarding,
        null -> UserLandingValue.OnBoarding
    }
}
