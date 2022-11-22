package com.jihad.budgetplanning.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihad.budgetplanning.Util
import com.jihad.budgetplanning.data.data_source.DatabaseCategories
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityDate
import com.jihad.budgetplanning.domain.models.EntityPurchase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelCategory(context: Context) : ViewModel() {

    private val daoCategory = DatabaseCategories.getInstance(context).dao()
    var categoryTitle: String = ""

    private var _list: MutableStateFlow<MutableList<EntityCategory>> = MutableStateFlow(
        mutableListOf()
    )
    val list: StateFlow<List<EntityCategory>> = _list.asStateFlow()

    private var _purchases: MutableStateFlow<MutableList<EntityPurchase>> = MutableStateFlow(
        mutableListOf()
    )
    val purchases: StateFlow<List<EntityPurchase>> = _purchases.asStateFlow()

    private var _dates: MutableStateFlow<MutableList<EntityDate>> = MutableStateFlow(
        mutableListOf()
    )
    val dates: StateFlow<MutableList<EntityDate>> = _dates.asStateFlow()

    private var _category: MutableStateFlow<String> = MutableStateFlow("")
    val category: StateFlow<String> = _category.asStateFlow()

    private var _total: MutableStateFlow<String> = MutableStateFlow("")
    val total: StateFlow<String> = _total.asStateFlow()

    init {
        val date = Util.getDate()
        var isAdded = false


        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.getAllDates().forEach {
                if (it.date == date)
                    isAdded = true
            }

            if (!isAdded)
                addDate(EntityDate(date))

            _dates.value = daoCategory.getAllDates()
            _list.value = daoCategory.getAllCategories()
            _purchases.value = daoCategory.getAllPurchases()
        }
    }

    fun addCategory(category: EntityCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.addCategory(category = category)
            _list.value = daoCategory.getAllCategories()
        }
    }

    fun addPurchase(purchase: EntityPurchase) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.addPurchase(purchase)

            val category = daoCategory.getCategory(purchase.category)
            category.total += purchase.purchaseAmount
            updateCategory(category)

            val date = daoCategory.getDate(category.date)
            date.total += purchase.purchaseAmount
            updateDate(date = date)

            _purchases.value = daoCategory.getAllPurchases()
        }
    }

    fun addDate(date: EntityDate) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.addDate(date)
            _dates.value = daoCategory.getAllDates()
        }
    }

    fun updateCategory(category: EntityCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.updateCategory(category = category)
            _list.value = daoCategory.getAllCategories()
        }
    }

    fun updateDate(date: EntityDate) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.updateDate(date)
            _dates.value = daoCategory.getAllDates()

        }
    }

    fun changeCategory(text: String) {
        _category.value = text
    }

    fun change(total: String) {
        _total.value = total
    }

    fun deleteCategory(category: EntityCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.deleteCategory(category)
            _purchases.value.filter { it.category == category.label }.forEach {
                daoCategory.deletePurchase(it)
            }
            val date = daoCategory.getDate(category.date)
            date.total -= category.total
            updateDate(date = date)

            _list.value = daoCategory.getAllCategories()
            _purchases.value = daoCategory.getAllPurchases()
            _dates.value = daoCategory.getAllDates()
        }
    }

    fun deletePurchase(purchase: EntityPurchase) {
        viewModelScope.launch(Dispatchers.IO) {
            daoCategory.deletePurchase(purchase)
            val category = daoCategory.getCategory(purchase.category)
            category.total -= purchase.purchaseAmount
            updateCategory(category)

            val date = daoCategory.getDate(category.date)
            date.total -= purchase.purchaseAmount
            updateDate(date = date)

            _purchases.value = daoCategory.getAllPurchases()
        }
    }

}