package com.example.dailyexpensetracker.presentation.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyexpensetracker.data.UserPreferences
import com.example.dailyexpensetracker.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// SplashViewModel.kt

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userPrefs: UserPreferences // inject your DataStore/SharedPref handler
) : ViewModel() {

    private val _startDestination = MutableStateFlow<Screen>(Screen.Splash)
    val startDestination: StateFlow<Screen> = _startDestination

    init {
        viewModelScope.launch {
            delay(1500) // simulate loading or animation
            val isLoggedIn = userPrefs.isUserLoggedIn() // implement this method
            _startDestination.value = if (isLoggedIn) Screen.ExpenseDashboard else Screen.Onboarding
        }
    }
}
