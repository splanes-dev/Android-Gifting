package com.splanes.gifting.data.common.utils.user

import com.google.firebase.auth.FirebaseAuth
import com.splanes.gifting.domain.common.error.NotLoggedException

fun FirebaseAuth.userOrThrow(throwable: Throwable = NotLoggedException) =
    currentUser ?: throw throwable
