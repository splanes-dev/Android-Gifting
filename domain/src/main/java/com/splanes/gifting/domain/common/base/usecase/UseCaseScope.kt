package com.splanes.gifting.domain.common.base.usecase

import com.splanes.gifting.domain.common.error.KnownException
import kotlinx.coroutines.flow.FlowCollector

interface UseCaseScope<T> {

    suspend fun emitSuccess(data: T)

    suspend fun emitError(error: KnownException)
}

internal class UseCaseScopeImpl<T>(private val flowCollector: FlowCollector<UseCase.Result<T>>) :
    UseCaseScope<T> {

    override suspend fun emitSuccess(data: T) {
        flowCollector.emit(UseCase.Success(data))
    }

    override suspend fun emitError(error: KnownException) {
        flowCollector.emit(UseCase.Failure(error))
    }
}
