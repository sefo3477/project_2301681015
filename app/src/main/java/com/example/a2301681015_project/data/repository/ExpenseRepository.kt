package com.example.a2301681015_project.data.repository

import com.example.a2301681015_project.data.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getAllExpenses(): Flow<List<ExpenseEntity>>
    suspend fun getExpenseById(id: Int): ExpenseEntity?
    suspend fun insertExpense(expense: ExpenseEntity)
    suspend fun updateExpense(expense: ExpenseEntity)
    suspend fun deleteExpense(expense: ExpenseEntity)
}
