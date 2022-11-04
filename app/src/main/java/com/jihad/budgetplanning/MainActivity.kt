package com.jihad.budgetplanning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.presentation.ViewModelCategory
import com.jihad.budgetplanning.presentation.components.ListItemCategory
import com.jihad.budgetplanning.ui.theme.BudgetPlanningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModelCategory = ViewModelCategory(this)
            val list = viewModelCategory.list.collectAsState()
            BudgetPlanningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(modifier = Modifier.fillMaxSize()) {
                        Button(onClick = {
                            viewModelCategory.addCategory(EntityCategory(label = "jihad", total = 1200))
                        }) {
                            Text(text = "add")
                        }

                    LazyColumn {
                        items(
                            count = list.value.size,
                            key = { list.value[it].id }
                        ) { index ->
                            ListItemCategory(
                                modifier = Modifier,
                                category = list.value[index]
                            )
                        }
                    }
                    }


                }
            }
        }
    }
}
