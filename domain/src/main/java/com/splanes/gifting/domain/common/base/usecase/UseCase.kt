package com.splanes.gifting.domain.common.base.usecase

import com.splanes.gifting.domain.common.error.GenericException
import com.splanes.gifting.domain.common.error.KnownException
import com.splanes.gifting.domain.common.error.TimeoutException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

abstract class UseCase<Request, Response> {

    open val timeout: Duration = 30.seconds
    open val dispatcher: CoroutineContext = Dispatchers.IO

    suspend operator fun invoke(request: Request): Flow<Result<Response>> = flow {
        try {
            val scope = UseCaseScopeImpl(this)
            withTimeout(timeout) {
                withContext(dispatcher) {
                    scope.execute(request)
                }
            }
        } catch (cause: Throwable) {
            when (cause) {
                is KnownException -> emit(Failure(cause))
                is TimeoutException -> emit(Failure(TimeoutException))
                else -> emit(Failure(GenericException))
            }
        }
    }

    abstract suspend fun UseCaseScope<Response>.execute(request: Request)

    sealed class Result<out T>
    data class Failure(val error: KnownException) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
}
