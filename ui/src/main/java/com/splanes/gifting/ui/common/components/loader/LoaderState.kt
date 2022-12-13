package com.splanes.gifting.ui.common.components.loader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

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

@Composable
fun rememberLoaderState() = remember { LoaderState() }

fun LoaderState.isVisible() = visuals != null
