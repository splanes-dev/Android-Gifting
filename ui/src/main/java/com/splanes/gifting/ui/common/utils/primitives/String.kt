package com.splanes.gifting.ui.common.utils.primitives

fun String.ifNotBlank(block: String.() -> Unit) {
    if (isNotBlank()) block()
}

fun String.toPrice(): Double? = runCatching {
    ifBlank { null }?.replace(",", ".")?.toDouble()
}.getOrNull()
