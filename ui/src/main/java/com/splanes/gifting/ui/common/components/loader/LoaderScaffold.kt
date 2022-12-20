package com.splanes.gifting.ui.common.components.loader

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.splanes.gifting.ui.common.uistate.UiState
import kotlinx.coroutines.launch

@Composable
fun LoaderScaffold(
    uiState: UiState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val loaderState = rememberLoaderState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.loading) {
        coroutineScope.launch {
            if (uiState.loading.visible) {
                loaderState.show(LoaderVisuals(message = uiState.loading.message))
            } else {
                loaderState.hide()
            }
        }
    }

    Box(modifier) {
        content()

        Loader(state = loaderState)
    }
}
