package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.Util
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityPurchase

@Composable
fun ListItemCategory(
    modifier: Modifier,
    category: EntityCategory,
    addPurchase: () -> Unit,
    visibility: MutableState<Boolean>
) {

    val icon: ImageVector = if (visibility.value)
        Icons.Default.ExpandLess
    else
        Icons.Default.ExpandMore

    Column(modifier = modifier
        .fillMaxWidth(0.9f)
        .fillMaxHeight()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue, shape = RoundedCornerShape(8.dp))
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = category.label)
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Arrow")
            Text(text = "${category.total} L.L")
            IconButton(onClick = {
                addPurchase()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }

            IconButton(onClick = {
                visibility.value = !visibility.value
            }) {
                Icon(imageVector = icon, contentDescription = "expand")
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
    }
}