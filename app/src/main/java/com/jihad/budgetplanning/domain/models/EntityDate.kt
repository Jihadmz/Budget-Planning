package com.jihad.budgetplanning.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
data class EntityDate(
    @PrimaryKey val date: String,
    var total: Int = 0
)
