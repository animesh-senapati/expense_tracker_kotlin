package com.example.dailyexpensetracker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
    modifier: Modifier = Modifier
) {
    val contentColor = if (selected) Color(0xFF9B59B6) else Color.LightGray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable {

        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor
        )
        Text(
            text = label,
            color = contentColor,
            fontSize = 12.sp
        )
    }
}

@Composable
fun FabAction(icon: ImageVector, color: Color) {
    FloatingActionButton(
        onClick = { /* Handle action */ },
        backgroundColor = color,
        contentColor = Color.White,
        modifier = Modifier.size(50.dp),
        elevation = FloatingActionButtonDefaults.elevation(4.dp)
    ) {
        Icon(imageVector = icon, contentDescription = "FAB Action")
    }
}

/////////////////////////
@Composable
fun FabMenu(
    isExpanded: Boolean,
    onFabClick: () -> Unit
) {
    val transition = updateTransition(targetState = isExpanded, label = "fab_transition")
    val radius by transition.animateDp(label = "radius_anim") {
        if (it) 80.dp else 0.dp
    }

    val angleOffsets = listOf(-90f, -135f, -45f)

    Box(
        modifier = Modifier.wrapContentSize(align = Alignment.BottomCenter),
        contentAlignment = Alignment.BottomCenter
    ) {
        angleOffsets.forEachIndexed { index, angle ->
            val rad = Math.toRadians(angle.toDouble())
            val offsetX = (cos(rad) * radius.value).dp
            val offsetY = (sin(rad) * radius.value).dp // <-- Use positive Y!

            AnimatedVisibility(visible = isExpanded) {
                FloatingActionButton(
                    onClick = { /* handle action */ },
                    backgroundColor = when (index) {
                        0 -> Color(0xFF2980B9)
                        1 -> Color(0xFF2ECC71)
                        else -> Color(0xFFE74C3C)
                    },
                    contentColor = Color.White,
                    modifier = Modifier
                        .offset(x = offsetX, y = offsetY) // <-- Use positive Y!
                        .size(50.dp),
                    elevation = FloatingActionButtonDefaults.elevation(4.dp)
                ) {
                    Icon(
                        imageVector = when (index) {
                            0 -> Icons.Default.Sync
                            1 -> Icons.Default.Download
                            else -> Icons.Default.Upload
                        },
                        contentDescription = "FAB $index"
                    )
                }
            }
        }

        // Main FAB
        FloatingActionButton(
            onClick = onFabClick,
            backgroundColor = Color(0xFF9B59B6),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Main FAB"
            )
        }
    }
}

