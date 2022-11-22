package com.jihad.budgetplanning.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jihad.budgetplanning.data.repository.DaoCategories
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityDate
import com.jihad.budgetplanning.domain.models.EntityPurchase

@Database(entities = [EntityCategory::class, EntityPurchase::class, EntityDate::class], version = 1)
abstract class DatabaseCategories : RoomDatabase() {

    abstract fun dao(): DaoCategories

companion object {
    private var INSTANCE: DatabaseCategories? = null

    fun getInstance(context: Context): DatabaseCategories{
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                context,
                DatabaseCategories::class.java,
                "db_categories"
            ).build()
        }

        return INSTANCE!!
    }
}
}