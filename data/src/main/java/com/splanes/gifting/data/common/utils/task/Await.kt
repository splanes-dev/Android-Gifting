package com.splanes.gifting.data.common.utils.task

import com.google.android.gms.tasks.Task
import com.splanes.gifting.domain.common.error.GenericException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> Task<T>.awaitOrThrow(exception: Throwable? = null): T =
    suspendCoroutine { continuation ->
        addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(task.result)
            } else {
                continuation.resumeWithException(exception ?: task.exception ?: GenericException)
            }
        }
    }

suspend fun Task<Void>.awaitIsSuccessful() = suspendCoroutine { continuation ->
    addOnCompleteListener { task ->
        continuation.resume(task.isSuccessful)
    }
}
