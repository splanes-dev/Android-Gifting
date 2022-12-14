package com.splanes.gifting.data.common.utils.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun SharedPreferences.write(block: SharedPreferences.Editor.() -> Unit): Boolean =
    suspendCoroutine { continuation ->
        val success = runCatching { edit { block() } }.isSuccess
        continuation.resume(success)
    }

suspend fun <T> SharedPreferences.read(block: SharedPreferences.() -> T): T =
    suspendCoroutine { continuation ->
        continuation.resume(block())
    }
