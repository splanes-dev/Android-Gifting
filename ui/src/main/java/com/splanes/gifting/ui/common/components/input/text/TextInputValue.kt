package com.splanes.gifting.ui.common.components.input.text

sealed class TextInputValue(open val text: String? = null) {

    val isEmpty: Boolean get() = text.orEmpty().isBlank()

    data class Normal(override val text: String) : TextInputValue(text)

    data class Error(override val text: String?, val error: String) : TextInputValue(text)
}
