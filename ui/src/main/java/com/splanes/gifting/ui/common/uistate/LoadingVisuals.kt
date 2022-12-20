package com.splanes.gifting.ui.common.uistate

data class LoadingVisuals(
    val visible: Boolean,
    val message: String? = null
) {
    companion object {
        val Empty get() = LoadingVisuals(
            visible = false,
            message = null
        )
    }
}
