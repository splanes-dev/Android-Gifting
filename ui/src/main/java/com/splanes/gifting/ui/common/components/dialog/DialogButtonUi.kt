package com.splanes.gifting.ui.common.components.dialog

data class DialogButtonUi(
    val text: String,
    val onClick: () -> Unit = {}
)
