package com.example.a2301681015_project

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExpenseUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addExpense_appearsInList() {
        // Click FAB to add expense
        composeTestRule.onNodeWithContentDescription("Add Expense").performClick()

        // Fill in details
        composeTestRule.onNodeWithText("Title").performTextInput("Dinner")
        composeTestRule.onNodeWithText("Amount").performTextInput("25.50")
        composeTestRule.onNodeWithText("Category").performTextInput("Food")

        // Click Add Expense button
        composeTestRule.onNodeWithText("Add Expense").performClick()

        // Verify it appears in the list
        composeTestRule.onNodeWithText("Dinner").assertIsDisplayed()
        composeTestRule.onNodeWithText("Food").assertIsDisplayed()
        composeTestRule.onNodeWithText("$25.50").assertIsDisplayed()
    }
}
