package com.splanes.gifting.ui.common.components.loader

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.splanes.gifting.ui.R
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@Composable
fun Loader(
    state: LoaderState,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = state.visuals != null,
        enter = fadeIn(animationSpec = tween(750)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.inverseOnSurface
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.anim_loader)
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Column() {
                LottieAnimation(
                    composition = composition,
                    progress = { progress }
                )
            }
        }
    }
}

class LoaderState {

    var visuals: LoaderVisuals? by mutableStateOf(null)
        private set

    private val mutex: Mutex = Mutex()

    suspend fun show(visuals: LoaderVisuals) = mutex.withLock {
        this.visuals = visuals
    }

    fun hide() {
        visuals = null
    }
}

data class LoaderVisuals(val message: String)
