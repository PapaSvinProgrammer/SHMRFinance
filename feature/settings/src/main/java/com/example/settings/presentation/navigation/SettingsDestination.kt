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
import com.example.settings.presentation.about.AboutScreen
import com.example.settings.presentation.colorSelector.ColorSelectorScreen
import com.example.settings.presentation.colorSelector.ColorSelectorViewModel
import com.example.settings.presentation.haptic.HapticSettingsScreen
import com.example.settings.presentation.haptic.HapticViewModel
import com.example.settings.presentation.languageSelector.LanguageSelectorScreen
import com.example.settings.presentation.settings.SettingsScreen
import com.example.settings.presentation.settings.SettingsViewModel
import com.example.settings.presentation.settingsOtp.SettingsOtpScreen
import com.example.settings.presentation.settingsOtp.SettingsOtpViewModel
import com.example.ui.navigation.AboutRoute
import com.example.ui.navigation.ColorSelectorRoute
import com.example.ui.navigation.HapticSettingsRoute
import com.example.ui.navigation.LanguageSelectorRoute
import com.example.ui.navigation.SettingsOtpRoute
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

    composable<LanguageSelectorRoute> {
        LanguageSelectorScreen(navController)
    }

    composable<AboutRoute> {
        AboutScreen(navController)
    }

    composable<SettingsOtpRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(appComponent)

        val viewModel: SettingsOtpViewModel = viewModel(
            factory = component.viewModelFactory
        )

        SettingsOtpScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable<HapticSettingsRoute> {
        val component = DaggerSettingsComponent
            .factory()
            .create(appComponent)

        val viewModel: HapticViewModel = viewModel(
            factory = component.viewModelFactory
        )

        HapticSettingsScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}
