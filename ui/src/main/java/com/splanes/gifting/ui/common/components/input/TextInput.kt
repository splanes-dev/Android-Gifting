package com.splanes.gifting.ui.common.components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(state: TextInputState) {
    val inputValue = state.inputValue
    val coroutineScope = rememberCoroutineScope()
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputValue.text.orEmpty(),
            onValueChange = { value -> coroutineScope.launch { state.onChange(value) } },
            label = {
            },
            leadingIcon = {
            },
            trailingIcon = {
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            isError = inputValue is TextInputValue.Error
        )
        AnimatedVisibility(visible = inputValue is TextInputValue.Error) {
            (inputValue as? TextInputValue.Error)?.let { errorValue ->
                Text(
                    text = errorValue.text.orEmpty(),
                    style = textStyleOf { bodyMedium },
                    color = colorOf { error }
                )
            }
        }
    }
}
