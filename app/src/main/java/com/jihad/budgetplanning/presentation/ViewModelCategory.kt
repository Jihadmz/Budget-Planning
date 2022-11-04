package com.jihad.budgetplanning.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihad.budgetplanning.data.data_source.DatabaseCategories
import com.jihad.budgetplanning.domain.models.EntityCategory
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _list.value = daoCategory.getAllCategories()
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

}