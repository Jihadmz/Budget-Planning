package com.jihad.budgetplanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityPurchase
import com.jihad.budgetplanning.presentation.ViewModelCategory
import com.jihad.budgetplanning.presentation.components.AnimatedComponent
import com.jihad.budgetplanning.presentation.components.ListItemCategory
import com.jihad.budgetplanning.presentation.components.ListItemPurchase
import com.jihad.budgetplanning.ui.theme.BudgetPlanningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModelCategory = ViewModelCategory(this)
            val list = viewModelCategory.list.collectAsState()
            val purchases = viewModelCategory.purchases.collectAsState()

            BudgetPlanningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(modifier = Modifier.fillMaxSize()) {
                        Button(onClick = {
                            viewModelCategory.addCategory(
                                EntityCategory(
                                    label = "jihadmz",
                                    total = 1200
                                )
                            )
                        }) {
                            Text(text = "add")
                        }

                        LazyColumn {
                            items(
                                count = list.value.size,
                                key = { list.value[it].id }
                            ) { index ->

                                val visibility = remember {
                                    mutableStateOf(false)
                                }

                                Column(modifier = Modifier.fillMaxSize()) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        ListItemCategory(
                                            modifier = Modifier,
                                            category = list.value[index],
                                            addPurchase = {
                                                viewModelCategory.addPurchase(
                                                    EntityPurchase(
                                                        label = "banana",
                                                        category = list.value[index].label,
                                                        purchaseAmount = 200
                                                    )
                                                )
                                            },
                                            visibility = visibility
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
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.Center
                                                    ) {
                                                        ListItemPurchase(
                                                            modifier = Modifier.fillMaxWidth(0.8f),
                                                            purchase = it
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.height(5.dp))
                                                }
                                            }
                                        },
                                        visibility = visibility.value
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}