package com.example.dailyexpensetracker


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ExpenseContent(modifier: Modifier = Modifier) {
    // Sample data
    val income = 5000
    val expenses = 1200
    val balance = 9400

    var selectedFrequency by remember { mutableStateOf("Today") }
    val frequencies = listOf("Today", "Week", "Month", "Year")

    // Recent transactions data sample
    val transactions = listOf(
        Transaction("Shopping", "Buy some grocery", -120, "10:00 AM"),
        Transaction("Subscription", "Disney+", -80, "03:30 PM"),
        Transaction("Food", "KFC", -32, "07:30 PM"),
        Transaction("Shopping", "Buy some grocery", -120, "10:00 AM"),
        Transaction("Subscription", "Disney+", -80, "03:30 PM"),
        Transaction("Food", "KFC", -32, "07:30 PM"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F4FF))
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleImage()
            Text(
                text = "October",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier.clickable {
                    /* implement dropdown */
                }
            )
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications",
                tint = Color(0xFF7C3AED)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(), // makes the column as wide as the parent, so it can be centered horizontally
            horizontalAlignment = Alignment.CenterHorizontally // centers children horizontally within the column
        ) {
            Text(
                text = "Account Balance",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$$balance",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Income and Expenses Cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Equal left & right padding
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IncomeExpenseCard(
                title = "Income",
                amount = income,
                backgroundColor = Color(0xFF16A34A),
                icon = Icons.Default.ArrowDownward,
                modifier = Modifier.weight(1f)  // Evenly distribute the width
            )
            IncomeExpenseCard(
                title = "Expenses",
                amount = expenses,
                backgroundColor = Color(0xFFF87171),
                icon = Icons.Default.ArrowUpward,
                modifier = Modifier.weight(1f)
            )
        }


        Spacer(modifier = Modifier.height(24.dp))

        // Spend Frequency label
        Text(
            text = "Spend Frequency",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )

        // Graph placeholder using Canvas (line graph with peaks)
        SpendFrequencyGraph()

        Spacer(modifier = Modifier.height(16.dp))

        // Frequency selection tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            frequencies.forEach { freq ->
                val selected = freq == selectedFrequency
                Text(
                    text = freq,
                    fontSize = 14.sp,
                    color = if (selected) Color.Black else Color.Gray,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (selected) Color(0xFFFFDDAE) else Color.Transparent)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { selectedFrequency = freq }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Recent Transactions Header + See All
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transaction",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = "See All",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFF7C3AED),
                modifier = Modifier.clickable { /* navigate to all transactions */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Recent Transactions List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(transactions) { transaction ->
                TransactionItem(transaction = transaction)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }
}

@Composable
fun CircleImage() {
    // Simple placeholder circle instead of image
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "P", color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun IncomeExpenseCard(
    title: String,
    amount: Int,
    backgroundColor: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
//            .weight(1f)
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
           .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = title, tint = backgroundColor)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = "$$amount",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun SpendFrequencyGraph() {
    val points = listOf(0.2f, 0.5f, 0.3f, 0.7f, 1.0f, 0.6f, 0.4f)
    val graphColor = Color(0xFF7C3AED)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val spacing = size.width / (points.size - 1)
        val height = size.height

        val path = androidx.compose.ui.graphics.Path().apply {
            moveTo(0f, height)
            points.forEachIndexed { index, point ->
                lineTo(index * spacing, height - point * height)
            }
            lineTo(size.width, height)
            close()
        }

        drawPath(
            path = path,
            color = graphColor.copy(alpha = 0.25f)
        )

        val strokePath = androidx.compose.ui.graphics.Path().apply {
            moveTo(0f, height - points[0] * height)
            points.forEachIndexed { index, point ->
                lineTo(index * spacing, height - point * height)
            }
        }

        drawPath(
            path = strokePath,
            color = graphColor,
            style = Stroke(width = 3f, cap = StrokeCap.Round)
        )
    }
}

data class Transaction(
    val title: String,
    val subtitle: String,
    val amount: Int,
    val time: String
)

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon placeholder based on title
        val icon = when (transaction.title) {
            "Shopping" -> Icons.Default.ShoppingBag
            "Subscription" -> Icons.Default.Description
            "Food" -> Icons.Default.Restaurant
            else -> Icons.Default.AttachMoney
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFF8F4FF), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, tint = Color(0xFF7C3AED), contentDescription = transaction.title)
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = transaction.subtitle,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${transaction.amount}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = if (transaction.amount < 0) Color(0xFFEF4444) else Color(0xFF22C55E)
            )
            Text(
                text = transaction.time,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

