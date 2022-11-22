package com.jihad.budgetplanning.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jihad.budgetplanning.ui.theme.LightOrange

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    primaryColor: Color = LightOrange
) {
    Button(modifier = modifier, onClick = {
        onClick()
    }, colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor) ) {
        Text(text = text)
    }
}