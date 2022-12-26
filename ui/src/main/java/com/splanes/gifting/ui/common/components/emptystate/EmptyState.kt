package com.splanes.gifting.ui.common.components.emptystate

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun EmptyState(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_empty_state)
    )
    val animState by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier = Modifier.size(200.dp),
            composition = composition,
            progress = { animState }
        )
        Spacer(height = 8.dp)
        Text(
            text = title,
            style = textStyleOf { headlineMedium },
            textAlign = TextAlign.Center
        )
        description?.let {
            Spacer(height = 16.dp)
            Text(
                text = description,
                style = textStyleOf { bodyLarge },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview("EmptyState", device = Devices.PIXEL_C)
@Preview("EmptyState (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("EmptyState (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewEmptyState() {
    GiftingTheme {
        EmptyState(
            modifier = Modifier.fillMaxSize(),
            title = "No results",
            description = "Seems that there is no results yet :("
        )
    }
}
