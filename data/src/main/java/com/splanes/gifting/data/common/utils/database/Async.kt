package com.splanes.gifting.data.common.utils.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.splanes.gifting.data.common.utils.task.awaitIsSuccessful
import com.splanes.gifting.domain.common.error.GenericException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> DatabaseReference.read(
    onCancelled: ((Throwable) -> T)? = null,
    onSnapshot: (DataSnapshot) -> T
): T = suspendCoroutine { continuation ->
    addListenerForSingleValueEvent(
        object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                continuation.resume(onSnapshot(snapshot))
            }

            override fun onCancelled(error: DatabaseError) {
                onCancelled
                    ?.invoke(error.toException())
                    ?.let(continuation::resume)
                    ?: continuation.resumeWithException(GenericException)
            }
        }
    )
}

suspend fun <T> DatabaseReference.write(value: T): Boolean =
    setValue(value).awaitIsSuccessful()

inline fun <reified T : Any> DataSnapshot.get(): T? =
    getValue(T::class.java)
