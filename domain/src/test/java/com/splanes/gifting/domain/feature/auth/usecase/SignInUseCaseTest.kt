package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignInRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SignInUseCaseTest {

    private val authRepository: AuthRepository = mockk {
        coEvery { signIn(any()) } returns UID
        coEvery { storeCredentials(any()) } returns true
    }
    private val signInUseCase = SignInUseCase(authRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a signInRequest, when autoSignIn, then authStateValue is updated`() =
        runTest {
            coEvery { authRepository.getAuthStateValue() } returns AuthStateValue.SignIn

            val request = Request.copy(autoSignIn = true)
            signInUseCase.invoke(request)

            coVerify {
                authRepository.updateAuthStateValue(AuthStateValue.AutoSignIn)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a signInRequest, when autoSignIn, then credentials are saved`() =
        runTest {
            coEvery { authRepository.getAuthStateValue() } returns AuthStateValue.SignIn

            val request = Request.copy(autoSignIn = true)
            signInUseCase.invoke(request)

            coVerify {
                authRepository.storeCredentials(any())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a signInRequest, when autoSignIn is already set, then credentials are not saved`() =
        runTest {
            coEvery { authRepository.getAuthStateValue() } returns AuthStateValue.AutoSignIn
            coEvery { authRepository.storeCredentials(any()) } returns true

            val request = Request.copy(autoSignIn = true)
            signInUseCase.invoke(request)

            coVerify(inverse = true) {
                authRepository.storeCredentials(any())
                authRepository.updateAuthStateValue(any())
            }
        }
}

private const val UID = "uid"
private val Request = SignInRequest(
    email = "email",
    password = "password",
    autoSignIn = false
)
