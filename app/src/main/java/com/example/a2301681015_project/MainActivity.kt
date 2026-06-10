package com.example.a2301681015_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a2301681015_project.data.local.ExpenseDatabase
import com.example.a2301681015_project.data.repository.ExpenseRepositoryImpl
import com.example.a2301681015_project.ui.screens.AddEditExpenseScreen
import com.example.a2301681015_project.ui.screens.ExpenseListScreen
import com.example.a2301681015_project.ui.theme._2301681015_projectTheme
import com.example.a2301681015_project.viewmodel.ExpenseViewModel
import com.example.a2301681015_project.viewmodel.ExpenseViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = ExpenseDatabase.getDatabase(this)
        val repository = ExpenseRepositoryImpl(database.expenseDao())
        val factory = ExpenseViewModelFactory(repository)

        enableEdgeToEdge()
        setContent {
            _2301681015_projectTheme {
                ExpenseTrackerApp(factory)
            }
        }
    }
}

@Composable
fun ExpenseTrackerApp(factory: ExpenseViewModelFactory) {
    val navController = rememberNavController()
    val viewModel: ExpenseViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = "expense_list") {
        composable("expense_list") {
            ExpenseListScreen(
                viewModel = viewModel,
                onAddExpenseClick = { navController.navigate("add_edit_expense") },
                onExpenseClick = { id -> navController.navigate("add_edit_expense?expenseId=$id") }
            )
        }
        composable(
            route = "add_edit_expense?expenseId={expenseId}",
            arguments = listOf(
                navArgument("expenseId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val expenseId = backStackEntry.arguments?.getInt("expenseId")
            AddEditExpenseScreen(
                viewModel = viewModel,
                expenseId = if (expenseId == -1) null else expenseId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
