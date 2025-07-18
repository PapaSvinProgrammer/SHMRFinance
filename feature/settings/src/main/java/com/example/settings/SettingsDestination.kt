package com.example.settings

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.SettingsRoute

fun NavGraphBuilder.settingsDestination(
    navController: NavController
) {
    composable<SettingsRoute>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        SettingsScreen(navController)
    }
}