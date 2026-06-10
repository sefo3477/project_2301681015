# Expense Tracker

A simple and efficient Android application to track your daily expenses, built with modern Android development tools.

## Project Idea
The Expense Tracker app helps users manage their finances by recording expenditures. Users can add, view, edit, and delete expenses. Each expense includes a title, amount, category, and date. The app provides a clean interface to keep track of spending habits.

## How It Works
1. **Expense List**: The main screen displays all recorded expenses in a list. A Floating Action Button (FAB) allows users to navigate to the "Add Expense" screen.
2. **Add/Edit Expense**: Users can enter the details of an expense. Validation ensures that the title is not empty. Success actions are notified via Snackbar messages.
3. **Data Persistence**: All data is stored locally using a Room database, ensuring that expenses are saved even after the app is restarted.

## Architecture
The project follows the **MVVM (Model-View-ViewModel)** architecture and the **Repository Pattern**:
- **Model**: `ExpenseEntity` represents the data structure. Room handles the database operations.
- **View**: Jetpack Compose is used for a reactive and modern UI.
- **ViewModel**: `ExpenseViewModel` manages the UI state and communicates with the repository.
- **Repository**: `ExpenseRepository` acts as a single source of truth for data, abstracting the data source from the ViewModel.

## User Flow
1. Launch the app to see the list of expenses.
2. Tap the "+" button to add a new expense.
3. Enter the title, amount, and category, then tap "Add Expense".
4. View the new expense in the list.
5. Tap an existing expense to edit or delete it.

## Installation Steps
1. Clone the repository.
2. Open the project in Android Studio (Ladybug or later).
3. Sync Gradle files.
4. Run the app on an emulator or physical device.

## Technical Requirements Met
- **Kotlin**
- **Min SDK 24**
- **MVVM Architecture**
- **Room Database**
- **Material 3 UI**
- **Navigation Component**
- **Unit and UI Testing**

## Screenshots
[Screenshot 1: Expense List]
[Screenshot 2: Add/Edit Expense]

## APK
The APK can be generated via `Build > Build Bundle(s) / APK(s) > Build APK(s)`.
