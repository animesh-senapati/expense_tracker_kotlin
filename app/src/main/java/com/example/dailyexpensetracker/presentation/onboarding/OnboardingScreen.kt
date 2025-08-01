package com.example.dailyexpensetracker.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dailyexpensetracker.presentation.navigation.Screen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = viewModel(),
    navController: NavHostController
) {
    val pages = viewModel.pages
    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(64.dp))

            // 🖼️ Onboarding Pages
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier
                    .heightIn(min = 400.dp)
                    .fillMaxWidth()
            ) { page ->
                OnboardingPageContent(pageData = pages[page])
            }

            // 🔵 Pager Indicator
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color(0xFF7F3DFF),
                inactiveColor = Color(0xFFE0E0E0),
                modifier = Modifier
                    .padding(top = 12.dp)
            )

            // ⬇ Gap between dots and buttons
            Spacer(modifier = Modifier.height(24.dp))

            // 🔘 Buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.navigate(Screen.Signup.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7F3DFF)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Sign Up", color = Color.White, fontSize = 18.sp)
                }

                Button(
                    onClick = { navController.navigate(Screen.Login.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF1EFFE)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login", color = Color(0xFF7F3DFF), fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp)) // bottom safe space
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.setPage(pagerState.currentPage)
    }
}


@Composable
fun OnboardingPageContent(pageData: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp)) // Slightly reduced spacing

        Image(
            painter = painterResource(id = pageData.imageRes),
            contentDescription = "Onboarding Image",
            modifier = Modifier
                .size(240.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 🟣 Title
        Text(
            text = pageData.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔘 Subtitle
        Text(
            text = pageData.subtitle,
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}







/*
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = viewModel(),
    navController: NavHostController
) {
    val pages = viewModel.pages
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
           *//* .padding(bottom = 48.dp), // Moves buttons up*//*
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val item = pages[page]
            OnboardingPageContent(pageData = item, pagerState = pagerState)
        }

        // 🔵 Three dots (pager indicator)
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color(0xFF7F3DFF),
            inactiveColor = Color(0xFFE0E0E0),
            modifier = Modifier
                .padding(top = 8.dp)
        )

        // ⬇ Spacer between dots and buttons
        Spacer(modifier = Modifier.height(18.dp))

        // 🟣 BUTTONS
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.navigate(Screen.Signup.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp), // 👈 Taller button
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7F3DFF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Sign Up", color = Color.White, fontSize = 18.sp)
            }

            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp), // 👈 Taller button
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF1EFFE)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Login", color = Color(0xFF7F3DFF), fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(24.dp)) // bottom safe space (optional)
    }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.setPage(pagerState.currentPage)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPageContent(pageData: OnboardingPage, pagerState: PagerState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = pageData.imageRes),
            contentDescription = "Onboarding Image",
            modifier = Modifier.size(240.dp)
        )



        Text(
            text = pageData.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 30.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = pageData.subtitle,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp),
            lineHeight = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))


      *//*  // 🟣 Dots just below image
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color(0xFF7F3DFF),
            inactiveColor = Color(0xFFE0E0E0),
            modifier = Modifier
                .padding(top = 8.dp)
        )*//*
    }
}*/
