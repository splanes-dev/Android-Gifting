package com.splanes.gifting.ui.common.components.input

sealed class TextInputValue(open val text: String? = null) {

    data class Normal(override val text: String) : TextInputValue(text)

    data class Error(override val text: String?, val error: String) : TextInputValue(text)
}
