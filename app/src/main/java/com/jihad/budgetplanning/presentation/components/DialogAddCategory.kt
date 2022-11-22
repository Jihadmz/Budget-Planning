package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.Util
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.presentation.ViewModelCategory

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogAddCategory(
    viewModelCategory: ViewModelCategory,
    onDismissListener: () -> Unit
) {

    val categoryText = viewModelCategory.category.collectAsState()
    val categoryTotal = viewModelCategory.total.collectAsState()

    val focusRequester = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val categoryError = remember {
        mutableStateOf(false)
    }

    MyDialog(
        onDismissListener = { onDismissListener() },
        content = {
            Column(
                modifier = Modifier
            ) {
                MyTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    text = categoryText.value,
                    onTextChange = {
                        viewModelCategory.changeCategory(it)
                    },
                    placeholder = "Category",
                    isError = categoryError.value
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

                MyButton(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(alignment = Alignment.CenterHorizontally), onClick = {
                        onDismissListener()
                        viewModelCategory.addCategory(
                            EntityCategory(
                                label = categoryText.value,
                                date = Util.getDate()
                            )
                        )
                        viewModelCategory.changeCategory("")
                        viewModelCategory.change("")
                    }, text = "Add")
            }
        },
        focusRequester = focusRequester,
        keyboardController = keyboardController!!
    )
}