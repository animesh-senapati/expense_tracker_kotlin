package com.example.dailyexpensetracker.presentation.onboarding

import androidx.annotation.DrawableRes

data class OnboardingPage(
    val title: String,
    val subtitle: String,
    @DrawableRes val imageRes: Int
)