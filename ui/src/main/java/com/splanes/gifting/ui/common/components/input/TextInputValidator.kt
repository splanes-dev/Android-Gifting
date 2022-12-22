package com.splanes.gifting.ui.common.components.input

import android.util.Patterns

interface TextInputValidator {
    val error: String
    fun isValid(input: String): Boolean

    companion object
}
fun TextInputValidator.Companion.of(regex: Regex, feedback: String) = object : TextInputValidator {
    override val error: String = feedback
    override fun isValid(input: String): Boolean = regex.matches(input)
}

val TextInputValidator.Companion.NotBlank
    get() = Regex("^.*(\\w)+.*$")
val TextInputValidator.Companion.Email
    get() = Patterns.EMAIL_ADDRESS.pattern().toRegex()
val TextInputValidator.Companion.Password
    get() = Regex("^.*(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}.*$")
