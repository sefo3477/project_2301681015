package com.example.a2301681015_project

import com.example.a2301681015_project.data.local.ExpenseDao
import com.example.a2301681015_project.data.local.ExpenseEntity
import com.example.a2301681015_project.data.repository.ExpenseRepositoryImpl
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ExpenseRepositoryTest {

    @Mock
    private lateinit var expenseDao: ExpenseDao

    private lateinit var repository: ExpenseRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ExpenseRepositoryImpl(expenseDao)
    }

    @Test
    fun `getAllExpenses returns flow from DAO`() = runTest {
        val expenses = listOf(
            ExpenseEntity(1, "Test 1", 10.0, "Cat 1", 123L),
            ExpenseEntity(2, "Test 2", 20.0, "Cat 2", 456L)
        )
        `when`(expenseDao.getAllExpenses()).thenReturn(flowOf(expenses))

        repository.getAllExpenses().collect {
            assertEquals(expenses, it)
        }
    }

    @Test
    fun `insertExpense calls DAO insert`() = runTest {
        val expense = ExpenseEntity(1, "Test", 10.0, "Cat", 123L)
        repository.insertExpense(expense)
        verify(expenseDao).insertExpense(expense)
    }

    @Test
    fun `deleteExpense calls DAO delete`() = runTest {
        val expense = ExpenseEntity(1, "Test", 10.0, "Cat", 123L)
        repository.deleteExpense(expense)
        verify(expenseDao).deleteExpense(expense)
    }
}
