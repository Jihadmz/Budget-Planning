package com.jihad.budgetplanning.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimatedComponent(
    modifier: Modifier,
    content: @Composable () -> Unit,
    visibility: Boolean
) {
    AnimatedVisibility(visible = visibility) {
        content()
    }
}