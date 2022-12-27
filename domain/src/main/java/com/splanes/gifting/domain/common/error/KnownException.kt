package com.splanes.gifting.domain.common.error

sealed class KnownException : Throwable()

object TimeoutException : KnownException()

object GenericException : KnownException()

object SignUpException : KnownException()

object SignInException : KnownException()

object NotLoggedException : KnownException()
