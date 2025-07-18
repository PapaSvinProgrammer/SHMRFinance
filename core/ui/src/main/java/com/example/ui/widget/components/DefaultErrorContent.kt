package com.example.ui.widget.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.shmrfinance.ui.R

@Composable
fun DefaultErrorContent(error: String) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_error))
    val state = animateLottieCompositionAsState(composition = composition)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier.size(150.dp),
                composition = composition,
                progress = { state.progress },
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = error,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}