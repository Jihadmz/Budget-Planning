package com.jihad.budgetplanning.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.jihad.budgetplanning.domain.models.EntityPurchase
import com.jihad.budgetplanning.presentation.ViewModelCategory
import com.jihad.budgetplanning.ui.theme.LightRed

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

    val purchaseError = remember {
        mutableStateOf(false)
    }

    val priceError = remember {
        mutableStateOf(false)
    }

    MyDialog(
        onDismissListener = { onDismissListener() },
        content = {
            Column(modifier = Modifier.wrapContentSize()) {
                MyTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    text = categoryText.value,
                    onTextChange = {
                        if (purchaseError.value)
                            purchaseError.value = false
                        viewModelCategory.changeCategory(it)
                    },
                    placeholder = "Purchase",
                    isError = purchaseError.value,
                    errorMsg = "Field is required",
                    primaryColor = LightRed
                )

                MyTextField(
                    text = categoryTotal.value,
                    onTextChange = {
                        if (priceError.value)
                            priceError.value = false
                        viewModelCategory.change(it)
                    },
                    placeholder = "Price",
                    isError = priceError.value,
                    errorMsg = "Field is required",
                    primaryColor = LightRed

                )

                AnimatedVisibility(
                    visible = !purchaseError.value && !priceError.value, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    
                    MyButton(
                        onClick = {
                            if (isAllFieldsEntered(
                                    viewModelCategory,
                                    purchaseError,
                                    priceError
                                )
                            ) { // user has entered all required fields
                                onDismissListener()
                                viewModelCategory.addPurchase(
                                    EntityPurchase(
                                        label = categoryText.value,
                                        purchaseAmount = categoryTotal.value.toInt(),
                                        category = viewModelCategory.categoryTitle
                                    )
                                )
                                viewModelCategory.change("")
                                viewModelCategory.changeCategory("")
                            }
                        }, text = "Add", primaryColor = LightRed
                    )
                }
            }
        },
        focusRequester = focusRequester,
        keyboardController = keyboardController!!
    )
}

fun isAllFieldsEntered(
    viewModelCategory: ViewModelCategory,
    purchaseError: MutableState<Boolean>,
    priceError: MutableState<Boolean>
): Boolean {
    var result = true

    if (viewModelCategory.total.value.isBlank()) {
        priceError.value = true
        result = false
    }
    if (viewModelCategory.category.value.isBlank()) {
        purchaseError.value = true
        result = false
    }

    return result
}