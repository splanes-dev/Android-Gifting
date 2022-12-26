package com.splanes.gifting.ui.common.components.topbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftingTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {}
) {
    val uiController = rememberSystemUiController()

    uiController.setStatusBarColor(colorOf { background })

    SmallTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorOf { background }
        ),
        title = {
            Surface(
                modifier = Modifier.wrapContentSize(),
                color = colorOf { primaryContainer.withAlpha(.5) },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
                    text = title,
                    style = textStyleOf { headlineLarge },
                    color = colorOf { onPrimaryContainer }
                )
            }
        },
        actions = actions,
        navigationIcon = navigationIcon
    )
}

@Preview("GiftingTopBar", device = Devices.PIXEL_C)
@Preview("GiftingTopBar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("GiftingTopBar (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewGiftingTopBar() {
    GiftingTheme {
        GiftingTopBar(
            title = "Home"
        )
    }
}
