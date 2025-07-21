package com.example.settings.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.settings.di.DaggerSettingsComponent
import com.example.settings.presentation.colorSelector.ColorSelectorScreen
import com.example.settings.presentation.colorSelector.ColorSelectorViewModel
import com.example.settings.presentation.settings.SettingsScreen
import com.example.settings.presentation.settings.SettingsViewModel
import com.example.ui.navigation.ColorSelectorRoute
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
        val component = remember {
            DaggerSettingsComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: SettingsViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SettingsScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable<ColorSelectorRoute> {
        val component = remember {
            DaggerSettingsComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: ColorSelectorViewModel = viewModel(
            factory = component.viewModelFactory
        )

        ColorSelectorScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}