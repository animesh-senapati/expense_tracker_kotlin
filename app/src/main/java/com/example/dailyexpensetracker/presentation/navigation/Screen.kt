package com.example.dailyexpensetracker.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Onboarding : Screen("onboarding_screen")
    object Login : Screen("login_screen")
    object Signup : Screen("signup_screen")
    object ExpenseDashboard : Screen("ExpenseDashboard_screen")
}