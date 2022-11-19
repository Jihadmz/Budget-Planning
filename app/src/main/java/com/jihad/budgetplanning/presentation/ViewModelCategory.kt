package com.jihad.budgetplanning.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihad.budgetplanning.data.data_source.DatabaseCategories
import com.jihad.budgetplanning.domain.models.EntityCategory
import com.jihad.budgetplanning.domain.models.EntityPurchase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelCategory(context: Context): ViewModel() {

    private val daoCategory = DatabaseCategories.getInstance(context).dao()

    private var _list: MutableStateFlow<MutableList<EntityCategory>> = MutableStateFlow(
        mutableListOf()
    )
    val list: StateFlow<List<EntityCategory>> = _list.asStateFlow()

    private var _purchases: MutableStateFlow<MutableList<EntityPurchase>> = MutableStateFlow(
        mutableListOf()
    )
    val purchases: StateFlow<List<EntityPurchase>> = _purchases.asStateFlow()

    private var _category: MutableStateFlow<String> = MutableStateFlow("")
    val category: StateFlow<String> = _category.asStateFlow()

    private var _total: MutableStateFlow<String> = MutableStateFlow("")
    val total: StateFlow<String> = _total.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _list.value = daoCategory.getAllCategories()
        }

        viewModelScope.launch(Dispatchers.IO) {
            _purchases.value = daoCategory.getAllPurchases()
        }
    }


    fun addCategory(category: EntityCategory){
        viewModelScope.launch(Dispatchers.IO){
            daoCategory.addCategory(category = category)
        }
        viewModelScope.launch(Dispatchers.IO){
            _list.value = daoCategory.getAllCategories()
        }
    }

    fun addPurchase(purchase: EntityPurchase){
        viewModelScope.launch(Dispatchers.IO){
            daoCategory.addPurchase(purchase)
        }
        viewModelScope.launch(Dispatchers.IO){
            _purchases.value = daoCategory.getAllPurchases()
        }
    }

    fun changeCategory(text: String){
        _category.value = text
    }

    fun change(total: String){
        _total.value = total
    }

}