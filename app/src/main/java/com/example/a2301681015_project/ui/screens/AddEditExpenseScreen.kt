package com.example.a2301681015_project.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.a2301681015_project.viewmodel.ExpenseViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditExpenseScreen(
    viewModel: ExpenseViewModel,
    expenseId: Int? = null,
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var date by remember { mutableLongStateOf(System.currentTimeMillis()) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(expenseId) {
        if (expenseId != null) {
            val expense = viewModel.getExpenseById(expenseId)
            if (expense != null) {
                title = expense.title
                amount = expense.amount.toString()
                category = expense.category
                date = expense.date
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(if (expenseId == null) "Add Expense" else "Edit Expense") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (expenseId != null) {
                        IconButton(onClick = {
                            scope.launch {
                                val expense = viewModel.getExpenseById(expenseId)
                                if (expense != null) {
                                    viewModel.deleteExpense(expense)
                                    onNavigateBack()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (title.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Title cannot be empty")
                        }
                        return@Button
                    }
                    val amountDouble = amount.toDoubleOrNull() ?: 0.0
                    if (expenseId == null) {
                        viewModel.insertExpense(title, amountDouble, category, date)
                    } else {
                        viewModel.updateExpense(expenseId, title, amountDouble, category, date)
                    }
                    onNavigateBack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (expenseId == null) "Add Expense" else "Save Changes")
            }
        }
    }
}
