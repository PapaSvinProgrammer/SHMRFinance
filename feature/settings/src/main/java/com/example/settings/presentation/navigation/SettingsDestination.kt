package com.example.settings.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.settings.di.DaggerSettingsComponent
import com.example.settings.presentation.SettingsScreen
import com.example.settings.presentation.SettingsViewModel
import com.example.ui.navigation.SettingsRoute

fun NavGraphBuilder.settingsDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<SettingsRoute>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        val component = DaggerSettingsComponent
            .factory()
            .create(appComponent)

        val viewModel: SettingsViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SettingsScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}