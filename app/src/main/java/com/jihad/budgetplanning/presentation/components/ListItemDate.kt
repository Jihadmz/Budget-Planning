package com.jihad.budgetplanning.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.domain.models.EntityDate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListItemDate(
    modifier: Modifier = Modifier,
    date: EntityDate,
    addCategory: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp),
        backgroundColor = Color.Blue,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Text(
                    text = date.date,
                    modifier = Modifier.padding(top = 10.dp),
                    fontWeight = FontWeight.Bold
                )

                AnimatedContent(targetState = date.total) {
                    Text(
                        text = "${date.total} L.L", modifier = Modifier.padding(top = 10.dp),
                    )
                }

                IconButton(onClick = { addCategory() }) {
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add")
                }
            }
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                content()
            }
        }
    }
}