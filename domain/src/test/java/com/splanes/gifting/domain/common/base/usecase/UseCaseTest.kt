package com.splanes.gifting.domain.common.base.usecase

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseTest {

    private class DummyUseCase() : UseCase<String, Int>() {
        override suspend fun UseCaseScope<Int>.execute(request: String) {
            emitSuccess(request.toInt())
        }
    }

    @Test
    fun `use case is success`() = runTest {
        val useCase = DummyUseCase()

        val values = mutableListOf<UseCase.Result<Int>>()
        val collectJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            useCase("9").toList(values)
        }
        assertThat(9.toInt()).isEqualTo(values[0])
    }

    @Test
    fun `use case is result is expected`() = runTest {
        launch {
            val useCase = DummyUseCase()
            val flow = useCase("9")
            flow.collect {
                assertThat((it as? UseCase.Success)?.data).run {
                    isNotNull()
                    isEqualTo(9)
                }
            }
        }
    }

    @Test
    fun `use case is error`() = runTest {
        launch {
            val useCase = DummyUseCase()
            val flow = useCase("9")
            flow.collect {
                assertThat(it).run {
                    isNotNull()
                    isInstanceOf(UseCase.Failure::class.java)
                }
            }
        }
    }
}
