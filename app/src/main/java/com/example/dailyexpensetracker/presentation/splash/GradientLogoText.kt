package com.example.dailyexpensetracker.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.material.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp
import com.example.dailyexpensetracker.R


val Inter = FontFamily(
    Font(R.font.inter, FontWeight.Normal),

)

@Composable
fun GradientGlowingText(text: String) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.6f),
            Color(0xFFFF5B97), // Center glow
            Color.White.copy(alpha = 0.6f)
        )
    )

    Text(
        text = text,
        fontSize = 38.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = gradient,
            shadow = Shadow(
                color = Color(0xFFFF5B97),
                offset = Offset(0f, 0f),
                blurRadius = 28f
            )
        )
    )
}
