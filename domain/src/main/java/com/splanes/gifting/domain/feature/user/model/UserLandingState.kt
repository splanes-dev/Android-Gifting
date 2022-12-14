package com.splanes.gifting.domain.feature.user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class UserLandingState : Parcelable {
    @Parcelize
    object ShowOnBoarding : UserLandingState()

    @Parcelize
    object SignUp : UserLandingState()

    @Parcelize
    data class AutoSignIn(val email: String, val password: String) : UserLandingState()

    @Parcelize
    data class SignIn(val email: String) : UserLandingState()
}
