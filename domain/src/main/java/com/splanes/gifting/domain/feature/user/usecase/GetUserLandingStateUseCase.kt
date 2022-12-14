package com.splanes.gifting.domain.feature.user.usecase

import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.base.usecase.UseCaseScope
import com.splanes.gifting.domain.common.error.CredentialsNotFound
import com.splanes.gifting.domain.feature.user.model.UserLandingState
import com.splanes.gifting.domain.feature.user.model.UserLandingValue
import com.splanes.gifting.domain.feature.user.repository.UserRepository
import javax.inject.Inject

class GetUserLandingStateUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCase<Unit, UserLandingState>() {

    override suspend fun UseCaseScope<UserLandingState>.execute(request: Unit) {
        when (val landingValue = repository.getLandingValue()) {
            UserLandingValue.OnBoarding -> {
                repository.updateLandingValue(UserLandingValue.SignUp)
                emitSuccess(UserLandingState.ShowOnBoarding)
            }

            UserLandingValue.SignUp -> {
                emitSuccess(UserLandingState.SignUp)
            }

            else -> {
                val credentials = repository.getCredentials()
                if (credentials != null) {
                    when (landingValue) {
                        UserLandingValue.AutoSignIn ->
                            emitSuccess(
                                UserLandingState.AutoSignIn(
                                    email = credentials.email,
                                    password = credentials.password
                                )
                            )

                        UserLandingValue.SignIn ->
                            emitSuccess(UserLandingState.SignIn(email = credentials.email))

                        else -> error("WTF User landing state impossible...")
                    }
                } else {
                    emitError(CredentialsNotFound)
                }
            }
        }
    }
}
