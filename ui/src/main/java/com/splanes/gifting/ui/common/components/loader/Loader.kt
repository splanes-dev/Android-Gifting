package com.splanes.gifting.ui.common.components.loader

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme
import kotlinx.coroutines.launch

@Composable
fun Loader(
    state: LoaderState,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = state.visuals != null,
        enter = fadeIn(animationSpec = tween(FADE_IN_MILLIS)),
        exit = fadeOut(animationSpec = tween(FADE_OUT_MILLIS))
    ) {
        state.visuals?.let { visuals ->
            Surface(
                modifier = modifier.fillMaxSize(),
                color = colorOf { inverseSurface.withAlpha(.7) }
            ) {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(R.raw.anim_loader)
                )
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        modifier = Modifier.size(150.dp),
                        composition = composition,
                        progress = { progress }
                    )
                    Crossfade(visuals.message) { message ->
                        if (message != null) {
                            Text(
                                modifier = Modifier.padding(top = 16.dp),
                                text = message,
                                style = textStyleOf { headlineSmall },
                                color = colorOf { inverseOnSurface },
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

private const val FADE_IN_MILLIS = 750
private const val FADE_OUT_MILLIS = 500

@Composable
@Preview(showBackground = true)
private fun LoaderPreview() {
    GiftingTheme {
        val coroutineScope = rememberCoroutineScope()
        val state = rememberLoaderState()
        Box(modifier = Modifier.fillMaxSize().background(color = colorOf { background })) {
            Loader(state = state)
            TextButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    coroutineScope.launch {
                        if (state.isVisible()) {
                            state.hide()
                        } else {
                            state.show(LoaderVisuals("Loading..."))
                        }
                    }
                }
            ) {
                Text("Show / Hide")
            }
        }
    }
}
