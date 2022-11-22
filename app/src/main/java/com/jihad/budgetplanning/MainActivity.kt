package com.jihad.budgetplanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.presentation.ViewModelCategory
import com.jihad.budgetplanning.presentation.components.*
import com.jihad.budgetplanning.ui.theme.BudgetPlanningTheme
import java.util.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModelCategory = ViewModelCategory(this)
            val list = viewModelCategory.list.collectAsState()
            val purchases = viewModelCategory.purchases.collectAsState()
            val dates = viewModelCategory.dates.collectAsState()
            val openDialog = remember {
                mutableStateOf(false)
            }
            val openPurchaseDialog = remember {
                mutableStateOf(false)
            }

//            Timer().schedule(timerTask {
//                addDate(viewModelCategory, dates.value)
//            }, 200)

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

                        Spacer(modifier = Modifier.height(20.dp))

                        if (dates.value.isNotEmpty())
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(
                                    count = dates.value.size,
                                    key = { dates.value[it].date }
                                ) { index ->

                                    Row(modifier = Modifier.fillMaxWidth().animateItemPlacement(), horizontalArrangement = Arrangement.Center) {

                                        ListItemDate(
                                            modifier = Modifier.fillMaxWidth(0.95f),
                                            date = dates.value[index],
                                            addCategory = {
                                                openDialog.value = true
                                            }) {

                                            list.value.forEach { category ->
                                                val visibility = remember {
                                                    mutableStateOf(false)
                                                }

                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .animateItemPlacement()
                                                ) {

                                                    Row(
                                                        modifier = Modifier.fillMaxWidth().animateItemPlacement(),
                                                        horizontalArrangement = Arrangement.Center
                                                    ) {
                                                        ListItemCategory(
                                                            modifier = Modifier.fillMaxWidth(0.9f),
                                                            category = category,
                                                            addPurchase = {
                                                                openPurchaseDialog.value =
                                                                    !openPurchaseDialog.value
                                                                viewModelCategory.categoryTitle =
                                                                    category.label
                                                            },
                                                            visibility = visibility,
                                                            viewModelCategory = viewModelCategory
                                                        )
                                                    }

                                                    Spacer(modifier = Modifier.height(10.dp))

                                                    val purchasesMy =
                                                        purchases.value.filter { it.category == category.label }

                                                    AnimatedComponent(
                                                        modifier = Modifier,
                                                        content = {
                                                            Column(modifier = Modifier.fillMaxWidth()) {
                                                                purchasesMy.forEach {
                                                                    Row(
                                                                        modifier = Modifier
                                                                            .fillMaxWidth()
                                                                            .animateItemPlacement(),
                                                                        horizontalArrangement = Arrangement.Center
                                                                    ) {
                                                                        ListItemPurchase(
                                                                            modifier = Modifier.fillMaxWidth(
                                                                                0.85f
                                                                            ),
                                                                            purchase = it,
                                                                            viewModelCategory = viewModelCategory
                                                                        )
                                                                    }
                                                                    Spacer(
                                                                        modifier = Modifier.height(
                                                                            5.dp
                                                                        )
                                                                    )
                                                                }
                                                            }
                                                        },
                                                        visibility = visibility.value
                                                    )
                                                }
                                            }
                                        }
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