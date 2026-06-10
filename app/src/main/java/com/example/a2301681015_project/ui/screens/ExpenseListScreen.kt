package com.example.a2301681015_project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a2301681015_project.ui.components.ExpenseItem
import com.example.a2301681015_project.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseListScreen(
    viewModel: ExpenseViewModel,
    onAddExpenseClick: () -> Unit,
    onExpenseClick: (Int) -> Unit
) {
    val expenses by viewModel.allExpenses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expense Tracker") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddExpenseClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Expense")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(expenses) { expense ->
                ExpenseItem(
                    expense = expense,
                    onClick = { onExpenseClick(expense.id) }
                )
            }
        }
    }
}
