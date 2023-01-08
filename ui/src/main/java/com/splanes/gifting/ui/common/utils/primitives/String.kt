package com.splanes.gifting.ui.common.utils.primitives // ktlint-disable filename

fun String.ifNotBlank(block: String.() -> Unit) {
    if (isNotBlank()) block()
}
