package com.example.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.example.shmrfinance.splash.R
import com.example.ui.navigation.ExpensesRoute
import com.example.ui.navigation.OtpRoute

@Composable
internal fun SplashScreen(
    navController: NavController,
    isOtp: Boolean
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_splash))
        val state = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            modifier = Modifier
                .statusBarsPadding()
                .align(Alignment.Center)
                .size(200.dp),
            composition = composition,
            progress = { state.progress },
        )

        val route = if (isOtp)
            OtpRoute(isCreate = false, isDisable = false)
        else
            ExpensesRoute

        if (state.isAtEnd && state.isPlaying) {
            navController.navigate(route) {
                popUpTo(navController.graph.id)
            }
        }
    }
}