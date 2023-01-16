package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAuthStateUseCaseTest {

    private val authRepository: AuthRepository = mockk()

    private val getAuthStateUseCase = GetAuthStateUseCase(authRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given authState call, when authState is OnBoarding, then is automatically updated`() =
        runTest {
            coEvery { authRepository.getAuthStateValue() } returns AuthStateValue.OnBoarding

            getAuthStateUseCase()

            coVerify {
                authRepository.updateAuthStateValue(AuthStateValue.SignUp)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given authState call, when authState is not OnBoarding, then is not updated`() =
        runTest {
            coEvery { authRepository.getAuthStateValue() } returns AuthStateValue.SignUp

            getAuthStateUseCase()

            coVerify(inverse = true) {
                authRepository.updateAuthStateValue(any())
            }
        }
}
