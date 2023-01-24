package com.splanes.gifting.domain.feature.auth.usecase

import com.splanes.gifting.domain.feature.auth.AuthRepository
import com.splanes.gifting.domain.feature.auth.model.AuthCredentials
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignUpRequest
import com.splanes.gifting.domain.feature.profile.ProfileRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SignUpUseCaseTest {

    private val profileRepository: ProfileRepository = mockk {
        coEvery { insertOrUpdateUsername(any()) } returns true
    }
    private val authRepository: AuthRepository = mockk {
        coEvery { signUp(any()) } returns UID
        coEvery { updateUserProfile(any()) } returns true
        coEvery { storeCredentials(any()) } returns true
    }
    private val signUpUseCase = SignUpUseCase(authRepository, profileRepository)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a sign up request with autoSignIn, when signingUp, then credentials are stored`() =
        runTest {
            val request = Request.copy(autoSignIn = true)

            signUpUseCase.invoke(request)

            coVerify {
                authRepository.storeCredentials(
                    AuthCredentials(
                        uid = UID,
                        email = Request.email,
                        password = Request.password
                    )
                )
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a signUp request, when signingUp, then authState is updated`() =
        runTest {
            signUpUseCase.invoke(Request)

            coVerify {
                authRepository.updateAuthStateValue(any())
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a signUpRequest with autoSignIn, when signingUp but storeCredentials fails, then authState is set to SignIn`() = // ktlint-disable max-line-length
        runTest {
            coEvery { authRepository.storeCredentials(any()) } returns false
            val request = Request.copy(autoSignIn = true)

            signUpUseCase.invoke(request)

            coVerify {
                authRepository.updateAuthStateValue(AuthStateValue.SignIn)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given a signUpRequest with autoSignIn, when signingUp, then authState is set to AutoSignIn`() = // ktlint-disable max-line-length
        runTest {
            val request = Request.copy(autoSignIn = true)

            signUpUseCase.invoke(request)

            coVerify {
                authRepository.updateAuthStateValue(AuthStateValue.AutoSignIn)
            }
        }
}

private const val UID = "uid"
private val Request = SignUpRequest(
    username = "username",
    email = "email",
    password = "password",
    autoSignIn = false
)
