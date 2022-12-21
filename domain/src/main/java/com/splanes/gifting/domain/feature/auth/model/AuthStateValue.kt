package com.splanes.gifting.domain.feature.auth.model

enum class AuthStateValue {
    OnBoarding,
    SignUp,
    AutoSignIn,
    SignIn;

    fun isSignedUp() = this in listOf(SignIn, AutoSignIn)
}
