package com.splanes.gifting.domain.common.base.usecase

import com.splanes.gifting.domain.common.error.GenericException
import com.splanes.gifting.domain.common.error.KnownException
import com.splanes.gifting.domain.common.error.TimeoutException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout

abstract class UseCase<Request, Response> {

    open val timeout: Duration = 30.seconds

    @Suppress("UNCHECKED_CAST")
    suspend operator fun invoke() = (Unit as? Request)?.let { request ->
        invoke(request)
    } ?: error("Invoke requires params.")

    suspend operator fun invoke(request: Request): Result<Response> =
        try {
            withTimeout(timeout) {
                val response = execute(request)
                Success(response)
            }
        } catch (cause: Throwable) {
            when (cause) {
                is TimeoutCancellationException -> Failure(TimeoutException)
                is KnownException -> Failure(cause)
                else -> Failure(GenericException)
            }
        }

    abstract suspend fun execute(request: Request): Response

    sealed class Result<out T>
    data class Failure(val error: KnownException) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
}
