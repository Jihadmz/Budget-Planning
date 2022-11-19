package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jihad.budgetplanning.Util
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.presentation.ViewModelCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.timerTask

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyDialog(
    viewModelCategory: ViewModelCategory,
    onDismissListener: () -> Unit
) {
    val categoryText = viewModelCategory.category.collectAsState()
    val categoryTotal = viewModelCategory.total.collectAsState()

    val focusRequester = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current


    Timer().schedule(timerTask {
        runBlocking(Dispatchers.Main) {
            Util.requestFocus(focusRequester, keyboardController!!)
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
                .background(color = Color.Blue, shape = RoundedCornerShape(10.dp))
        ) {
            MyTextField(
                modifier = Modifier.focusRequester(focusRequester),
                text = categoryText.value,
                onTextChange = {
                    viewModelCategory.changeCategory(it)
                },
                placeholder = "Category"
            )

            Spacer(modifier = Modifier.height(5.dp))

            MyTextField(
                text = categoryTotal.value,
                onTextChange = {
                    viewModelCategory.change(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = "Total"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(alignment = Alignment.CenterHorizontally), onClick = {
                    onDismissListener()
                    viewModelCategory.addCategory(
                        EntityCategory(
                            label = categoryText.value,
                            total = categoryTotal.value.toInt()
                        )
                    )
                    viewModelCategory.changeCategory("")
                    viewModelCategory.change("")
                }) {
                Text(text = "Save")
            }
        }
    }

}