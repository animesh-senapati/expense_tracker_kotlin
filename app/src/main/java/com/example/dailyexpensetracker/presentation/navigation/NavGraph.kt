package com.example.dailyexpensetracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailyexpensetracker.ExpenseDashboardScreen
import com.example.dailyexpensetracker.presentation.login.LoginScreen
import com.example.dailyexpensetracker.presentation.onboarding.OnboardingScreen
import com.example.dailyexpensetracker.presentation.onboarding.OnboardingViewModel
import com.example.dailyexpensetracker.presentation.signup.SignupScreen
import com.example.dailyexpensetracker.presentation.splash.SplashScreen
import com.example.dailyexpensetracker.presentation.splash.SplashViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val splashViewModel: SplashViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                viewModel = splashViewModel,
                navController = navController
            )
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                navController = navController
            )
        }
        composable(Screen.Login.route) { LoginScreen() }
        composable(Screen.Signup.route) { SignupScreen() }
        composable(Screen.ExpenseDashboard.route) { ExpenseDashboardScreen() }
        /*composable(Screen.VerifyPin.route) { VerifyPinScreen() }*/
    }
}
