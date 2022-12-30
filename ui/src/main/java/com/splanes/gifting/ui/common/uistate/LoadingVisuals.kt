package com.splanes.gifting.ui.common.uistate

data class LoadingVisuals(
    val visible: Boolean,
    val message: String? = null
) {
    companion object {
        val Hidden
            get() = LoadingVisuals(
                visible = false,
                message = null
            )

        val Visible
            get() = LoadingVisuals(
                visible = true,
                message = null
            )
    }
}
