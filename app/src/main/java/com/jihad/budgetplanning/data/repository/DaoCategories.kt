package com.jihad.budgetplanning.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jihad.budgetplanning.domain.models.EntityCategory

@Dao
interface DaoCategories {

    @Insert
    fun addCategory(category: EntityCategory)

    @Delete
    fun deleteCategory(category: EntityCategory)

    @Query("select * from categories")
    fun getAllCategories(): MutableList<EntityCategory>
}