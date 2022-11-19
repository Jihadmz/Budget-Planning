package com.jihad.budgetplanning.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityPurchase

@Dao
interface DaoCategories {

    @Insert
    fun addCategory(category: EntityCategory)

    @Insert(entity = EntityPurchase::class)
    fun addPurchase(purchase: EntityPurchase)

    @Delete
    fun deleteCategory(category: EntityCategory)

    @Delete
    fun deletePurchase(purchase: EntityPurchase)

    @Query("select * from categories")
    fun getAllCategories(): MutableList<EntityCategory>

    @Query("select * from purchase")
    fun getAllPurchases(): MutableList<EntityPurchase>

    @Query("select * from purchase where category=:category")
    fun getPurchasesByCategory(category: String): MutableList<EntityPurchase>
}