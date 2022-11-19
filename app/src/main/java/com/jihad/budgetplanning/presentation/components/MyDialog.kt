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
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyDialog(
    onDismissListener: () -> Unit,
    content: @Composable () -> Unit,
    focusRequester: FocusRequester,
    keyboardController: SoftwareKeyboardController
) {

//    LaunchedEffect(key1 = true, block = {
//        Util.requestFocus(focusRequester, keyboardController)
//    })
//    Timer().schedule(timerTask {
//        runBlocking(Dispatchers.Main) {
//        }
//    }, 500)

    Dialog(
        onDismissRequest = {
            onDismissListener()
        },
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
        ) {
            content()
        }
    }
}