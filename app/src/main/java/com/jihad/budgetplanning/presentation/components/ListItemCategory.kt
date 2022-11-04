package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jihad.budgetplanning.domain.models.EntityCategory

@Composable
fun ListItemCategory(
    modifier: Modifier,
    category: EntityCategory
){
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = category.label)
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Arrow")
        Text(text = "${category.total} L.L")
    }

}