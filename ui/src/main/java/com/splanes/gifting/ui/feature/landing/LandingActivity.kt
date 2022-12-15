package com.splanes.gifting.ui.feature.landing

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.splanes.gifting.domain.feature.user.model.UserLandingState
import com.splanes.gifting.ui.theme.GiftingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepOnSplashScreen = true
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { keepOnSplashScreen }

        setContent {
            GiftingTheme {
                val viewModel: LandingViewModel = hiltViewModel()
                val error by viewModel.errorState
                val loading by viewModel.loadingState
                val landing by viewModel.landingState

                SideEffect { viewModel.getLandingState() }

                LaunchedEffect(landing) { if (landing != null) keepOnSplashScreen = false }

                landing?.let { state ->
                    when (state) {
                        is UserLandingState.AutoSignIn -> TODO()
                        UserLandingState.ShowOnBoarding -> TODO()
                        is UserLandingState.SignIn -> TODO()
                        UserLandingState.SignUp -> TODO()
                    }
                }
            }
        }
    }
}
