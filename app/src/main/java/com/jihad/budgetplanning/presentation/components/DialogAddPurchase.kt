package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.jihad.budgetplanning.domain.models.EntityPurchase
import com.jihad.budgetplanning.presentation.ViewModelCategory

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogAddPurchase(
    viewModelCategory: ViewModelCategory,
    onDismissListener: () -> Unit,
) {

    val categoryText = viewModelCategory.category.collectAsState()
    val categoryTotal = viewModelCategory.total.collectAsState()

    val focusRequester = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    MyDialog(
        onDismissListener = { onDismissListener() },
        content = {
            Column(modifier = Modifier.wrapContentSize()) {
                MyTextField(text = categoryText.value, onTextChange = {
                    viewModelCategory.changeCategory(it)
                }, placeholder = "Purchase")

                MyTextField(text = categoryTotal.value, onTextChange = {
                    viewModelCategory.change(it)
                }, placeholder = "Price")

                Button(onClick = {
                    onDismissListener()
                    viewModelCategory.addPurchase(
                        EntityPurchase(
                            label = categoryText.value,
                            purchaseAmount = categoryTotal.value.toInt(),
                            category = viewModelCategory.categoryTitle
                        )
                    )
                }) {
                    Text(text = "Add")
                }
            }
        },
        focusRequester = focusRequester,
        keyboardController = keyboardController!!
    )
}