package com.splanes.gifting.ui.common.utils.primitives

inline fun String.ifNotBlank(block: String.() -> Unit) {
    if (isNotBlank()) block()
}

fun String.toPrice(): Double? = runCatching {
    ifBlank { null }?.replace(",", ".")?.toDouble()
}.getOrNull()
