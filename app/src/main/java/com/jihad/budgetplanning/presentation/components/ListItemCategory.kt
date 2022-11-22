package com.jihad.budgetplanning.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.presentation.ViewModelCategory
import com.jihad.budgetplanning.ui.theme.LightOrange
import com.jihad.budgetplanning.ui.theme.LightRed

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListItemCategory(
    modifier: Modifier,
    category: EntityCategory,
    addPurchase: () -> Unit,
    visibility: MutableState<Boolean>,
    viewModelCategory: ViewModelCategory
) {

    val icon: ImageVector = if (visibility.value)
        Icons.Filled.ExpandLess
    else
        Icons.Filled.ExpandMore

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = LightOrange, shape = RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Text(modifier = Modifier.weight(1f), text = category.label)
        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow"
        )

        // animating category total amount component
        AnimatedContent(modifier = Modifier.weight(1f), targetState = category.total) {
            Text(text = "${category.total} L.L", color = if (category.total > 1700000) LightRed else Color.White)
        }

        IconButton(modifier = Modifier.weight(1f), onClick = {
            addPurchase()
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add")
        }

        IconButton(modifier = Modifier.weight(1f), onClick = {
            visibility.value = !visibility.value
        }) {
            Icon(imageVector = icon, contentDescription = "expand")
        }

        IconButton(modifier = Modifier.weight(1f), onClick = {
            viewModelCategory.deleteCategory(category)
        }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
        }
    }
}