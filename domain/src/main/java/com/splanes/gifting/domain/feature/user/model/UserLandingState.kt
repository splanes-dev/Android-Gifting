package com.splanes.gifting.domain.feature.user.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class UserLandingState : Parcelable {
    @Parcelize
    object ShowOnBoarding : UserLandingState()

    @Parcelize
    object SignUp : UserLandingState()

    @Parcelize
    data class AutoSignIn(val username: String) : UserLandingState()

    @Parcelize
    data class SignIn(val username: String?) : UserLandingState()
}
