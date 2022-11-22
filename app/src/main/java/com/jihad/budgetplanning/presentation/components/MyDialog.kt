package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jihad.budgetplanning.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.timerTask

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyDialog(
    onDismissListener: () -> Unit,
    content: @Composable () -> Unit,
    focusRequester: FocusRequester,
    keyboardController: SoftwareKeyboardController
) {

    Timer().schedule(timerTask {
        runBlocking(Dispatchers.Main) {
        Util.requestFocus(focusRequester, keyboardController)
        }
    }, 500)

    Dialog(
        onDismissRequest = {
            onDismissListener()
        },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
        ) {
            content()
        }
    }
}