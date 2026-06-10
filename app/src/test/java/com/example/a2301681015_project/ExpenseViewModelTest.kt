package com.example.a2301681015_project

import com.example.a2301681015_project.data.local.ExpenseEntity
import com.example.a2301681015_project.data.repository.ExpenseRepository
import com.example.a2301681015_project.viewmodel.ExpenseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class ExpenseViewModelTest {

    @Mock
    private lateinit var repository: ExpenseRepository

    private lateinit var viewModel: ExpenseViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        
        `when`(repository.getAllExpenses()).thenReturn(flowOf(emptyList()))
        viewModel = ExpenseViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insertExpense calls repository insert`() = runTest {
        viewModel.insertExpense("Test", 10.0, "Cat", 123L)
        verify(repository).insertExpense(org.mockito.kotlin.any())
    }

    @Test
    fun `deleteExpense calls repository delete`() = runTest {
        val expense = ExpenseEntity(1, "Test", 10.0, "Cat", 123L)
        viewModel.deleteExpense(expense)
        verify(repository).deleteExpense(expense)
    }
}
