package com.jihad.budgetplanning.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jihad.budgetplanning.domain.models.EntityPurchase
import com.jihad.budgetplanning.presentation.ViewModelCategory

@Composable
fun ListItemPurchase(
    modifier: Modifier,
    purchase: EntityPurchase,
    viewModelCategory: ViewModelCategory
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red, shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = purchase.label)
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Arrow")
        Text(text = "${purchase.purchaseAmount} L.L")

        IconButton(onClick = {
            viewModelCategory.deletePurchase(purchase)
        }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}