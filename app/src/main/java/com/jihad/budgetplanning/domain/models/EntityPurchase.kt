package com.jihad.budgetplanning.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
data class EntityPurchase(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var label: String,
    var category: String,
    var purchaseAmount: Int
)
