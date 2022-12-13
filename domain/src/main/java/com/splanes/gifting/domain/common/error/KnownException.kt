package com.splanes.gifting.domain.common.error

sealed class KnownException : Throwable()

object TimeoutException : KnownException()

object GenericException : KnownException()
