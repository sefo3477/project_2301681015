package com.example.a2301681015_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a2301681015_project.data.local.ExpenseEntity
import com.example.a2301681015_project.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    val allExpenses: StateFlow<List<ExpenseEntity>> = repository.getAllExpenses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertExpense(title: String, amount: Double, category: String, date: Long) {
        viewModelScope.launch {
            repository.insertExpense(
                ExpenseEntity(title = title, amount = amount, category = category, date = date)
            )
        }
    }

    fun updateExpense(id: Int, title: String, amount: Double, category: String, date: Long) {
        viewModelScope.launch {
            repository.updateExpense(
                ExpenseEntity(id = id, title = title, amount = amount, category = category, date = date)
            )
        }
    }

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }

    suspend fun getExpenseById(id: Int): ExpenseEntity? {
        return repository.getExpenseById(id)
    }
}

class ExpenseViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
