package com.jihad.budgetplanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.presentation.ViewModelCategory
import com.jihad.budgetplanning.presentation.components.*
import com.jihad.budgetplanning.ui.theme.BudgetPlanningTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModelCategory = ViewModelCategory(this)
            val list = viewModelCategory.list.collectAsState()
            val purchases = viewModelCategory.purchases.collectAsState()
            val openDialog = remember {
                mutableStateOf(false)
            }
            val openPurchaseDialog = remember {
                mutableStateOf(false)
            }

            BudgetPlanningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(modifier = Modifier.fillMaxSize()) {

                        if (openDialog.value)
                            DialogAddCategory(viewModelCategory = viewModelCategory) {
                                openDialog.value = !openDialog.value
                            }

                        if (openPurchaseDialog.value) {
                            DialogAddPurchase(
                                viewModelCategory = viewModelCategory,
                                onDismissListener = {
                                    openPurchaseDialog.value = !openPurchaseDialog.value
                                }
                            )
                        }

                        Button(
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                            onClick = {
                                openDialog.value = true
                            }) {
                            Text(text = "Add Category")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(
                                count = list.value.size,
                                key = { list.value[it].id }
                            ) { index ->

                                val visibility = remember {
                                    mutableStateOf(false)
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .animateItemPlacement()
                                ) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        ListItemCategory(
                                            modifier = Modifier,
                                            category = list.value[index],
                                            addPurchase = {
                                                openPurchaseDialog.value = !openPurchaseDialog.value
                                                viewModelCategory.categoryTitle =
                                                    list.value[index].label
                                            },
                                            visibility = visibility,
                                            viewModelCategory = viewModelCategory
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    val purchasesMy =
                                        purchases.value.filter { it.category == list.value[index].label }

                                    AnimatedComponent(
                                        modifier = Modifier,
                                        content = {
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                                purchasesMy.forEach {
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth().animateItemPlacement(),
                                                        horizontalArrangement = Arrangement.Center
                                                    ) {
                                                        ListItemPurchase(
                                                            modifier = Modifier.fillMaxWidth(0.8f),
                                                            purchase = it,
                                                            viewModelCategory = viewModelCategory
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.height(5.dp))
                                                }
                                        }
                                            },
                                        visibility = visibility.value
                                    )
                                }

                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                }
            }
        }
    }
}