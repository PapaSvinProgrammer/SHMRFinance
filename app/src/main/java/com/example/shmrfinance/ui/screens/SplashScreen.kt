package com.example.shmrfinance.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shmrfinance.R
import com.example.shmrfinance.app.navigation.ExpensesRoute

@Composable
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_splash))
        val state = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            modifier = Modifier
                .padding(top = 300.dp)
                .align(Alignment.TopCenter)
                .size(200.dp),
            composition = composition,
            progress = { state.progress },
        )

        if (state.isAtEnd && state.isPlaying) {
            navController.navigate(ExpensesRoute)
        }
    }
}