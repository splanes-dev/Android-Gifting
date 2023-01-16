package com.splanes.gifting.domain.common.base.usecase

import com.google.common.truth.Truth.assertThat
import com.splanes.gifting.domain.common.error.TimeoutException
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseTest {

    private class DummyUseCase : UseCase<String, Int>() {
        override suspend fun execute(request: String): Int =
            request.toInt()
    }

    private class DummyWithErrorUseCase : UseCase<String, Int>() {
        override suspend fun execute(request: String): Int =
            throw RuntimeException()
    }

    private class DummyTimeoutUseCase : UseCase<String, Int>() {
        override suspend fun execute(request: String): Int {
            delay(timeout.plus(1.seconds))
            error("Timeout")
        }
    }

    @Test
    fun `use case is success`() = runTest {
        val useCase = DummyUseCase()

        launch(UnconfinedTestDispatcher(testScheduler)) {
            val actual = useCase("9")
            assertThat(actual).run {
                isNotNull()
                isInstanceOf(UseCase.Success::class.java)
            }
        }
    }

    @Test
    fun `use case is result is expected`() = runTest {
        val useCase = DummyUseCase()

        launch(UnconfinedTestDispatcher(testScheduler)) {
            val actual = useCase("9")
            assertThat((actual as? UseCase.Success)?.data).run {
                isNotNull()
                isEqualTo(9)
            }
        }
    }

    @Test
    fun `use case is error`() = runTest {
        val useCase = DummyWithErrorUseCase()

        launch(UnconfinedTestDispatcher(testScheduler)) {
            val actual = useCase("9")
            assertThat(actual).run {
                isNotNull()
                isInstanceOf(UseCase.Failure::class.java)
            }
        }
    }

    @Test
    fun `use case is error timeout`() = runTest {
        val useCase = DummyTimeoutUseCase()

        launch(UnconfinedTestDispatcher(testScheduler)) {
            val actual = useCase("9")
            assertThat((actual as? UseCase.Failure)?.error).run {
                isNotNull()
                isInstanceOf(TimeoutException::class.java)
            }
        }
    }
}
