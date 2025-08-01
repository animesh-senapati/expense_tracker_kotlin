package com.example.dailyexpensetracker.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.example.dailyexpensetracker.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel : ViewModel() {
    private val _pages = listOf(
        OnboardingPage(
            title = "Gain total control of your money",
            subtitle = "Become your own money manager and make every cent count",
            imageRes = R.drawable.ic_onboarding1
        ),
        OnboardingPage(
            title = "Know where your money goes",
            subtitle = "Track your transaction easily,with categories and financial report ",
            imageRes = R.drawable.ic_onboarding2
        ),
        OnboardingPage(
            title = "Planning ahead",
            subtitle = "Setup your budget for each category so you in control",
            imageRes = R.drawable.ic_onboarding3
        )
    )

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    val pages: List<OnboardingPage> get() = _pages

    fun setPage(index: Int) {
        _currentPage.value = index
    }
}