package com.splanes.gifting.ui.common.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class DialogState(val visible: Boolean = false) {

    var isVisible by mutableStateOf(visible)
        private set

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}

@Composable
fun rememberDialogState(visible: Boolean = false) = remember { DialogState(visible) }
