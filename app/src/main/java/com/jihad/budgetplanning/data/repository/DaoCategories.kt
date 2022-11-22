package com.jihad.budgetplanning.data.repository

import androidx.room.*
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityDate
import com.jihad.budgetplanning.domain.models.EntityPurchase

@Dao
interface DaoCategories {

    @Insert
    fun addCategory(category: EntityCategory)

    @Insert(entity = EntityPurchase::class)
    fun addPurchase(purchase: EntityPurchase)

    @Insert
    fun addDate(date: EntityDate)

    @Update
    fun updateCategory(category: EntityCategory)

    @Update
    fun updateDate(date: EntityDate)

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

    @Query("select * from date")
    fun getAllDates(): MutableList<EntityDate>

    @Query("select * from categories where label=:label")
    fun getCategory(label: String): EntityCategory

    @Query("select * from date where date=:date")
    fun getDate(date: String): EntityDate
}