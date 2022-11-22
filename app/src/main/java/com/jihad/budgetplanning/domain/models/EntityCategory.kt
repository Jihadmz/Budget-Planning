package com.jihad.budgetplanning.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class EntityCategory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var label: String,
    var total: Int = 0,
    val date: String
)
