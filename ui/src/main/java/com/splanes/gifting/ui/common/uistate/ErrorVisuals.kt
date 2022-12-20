package com.splanes.gifting.ui.common.uistate

data class ErrorVisuals(
    val visible: Boolean,
    val title: String,
    val description: String? = null
) {
    companion object {
        val Empty get() = ErrorVisuals(
            visible = false,
            title = "",
            description = null
        )
    }
}
