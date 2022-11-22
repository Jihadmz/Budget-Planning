package com.jihad.budgetplanning.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.ui.theme.LightOrange

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    placeholder: String,
    errorMsg: String = "",
    isError: Boolean = false,
    primaryColor: Color = LightOrange
) {
    Column {

        TextField(
            modifier = modifier,
            keyboardOptions = keyboardOptions,
            value = text,
            trailingIcon = {
                if (isError) Icon(
                    imageVector = Icons.Filled.Error,
                    contentDescription = "error"
                )
            },
            onValueChange = {
                onTextChange(it)
            }, isError = isError,
            placeholder = { Text(text = placeholder) },
            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = primaryColor, cursorColor = primaryColor)
        )

        AnimatedVisibility(visible = isError) {
            Text(text = errorMsg, modifier = Modifier.padding(start = 10.dp), color = Color.Red)
        }
    }
}