package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.jihad.budgetplanning.presentation.ViewModelCategory
import java.text.NumberFormat

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    placeholder: String
) {
    TextField(
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        value = text,
        onValueChange = {
            onTextChange(it)
        },
    placeholder = { Text(text = placeholder)})
}