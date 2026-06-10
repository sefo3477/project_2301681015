package com.example.a2301681015_project.data.repository

import com.example.a2301681015_project.data.local.ExpenseDao
import com.example.a2301681015_project.data.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow

class ExpenseRepositoryImpl(private val expenseDao: ExpenseDao) : ExpenseRepository {
    override fun getAllExpenses(): Flow<List<ExpenseEntity>> = expenseDao.getAllExpenses()

    override suspend fun getExpenseById(id: Int): ExpenseEntity? = expenseDao.getExpenseById(id)

    override suspend fun insertExpense(expense: ExpenseEntity) = expenseDao.insertExpense(expense)

    override suspend fun updateExpense(expense: ExpenseEntity) = expenseDao.updateExpense(expense)

    override suspend fun deleteExpense(expense: ExpenseEntity) = expenseDao.deleteExpense(expense)
}
