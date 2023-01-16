package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AutoSignInUseCaseTest {

    private val authRepository: AuthRepository = mockk()
    private val autoSignInUseCase = AutoSignInUseCase(authRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given autoSignIn invocation, when credentials are not null, then do signIn`() =
        runTest {
            coEvery { authRepository.getStoredCredentials() } returns Credentials

            autoSignInUseCase.invoke()

            coVerify {
                authRepository.signIn(any())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given autoSignIn invocation, when credentials are null, then signIn is not called`() =
        runTest {
            coEvery { authRepository.getStoredCredentials() } returns null

            autoSignInUseCase.invoke()

            coVerify(inverse = true) {
                authRepository.signIn(any())
            }
        }
}

private val Credentials = AuthCredentials(
    uid = "uid",
    email = "email",
    password = "password"
)
