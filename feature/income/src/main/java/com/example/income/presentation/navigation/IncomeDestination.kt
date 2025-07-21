package com.example.income.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.income.di.DaggerIncomeComponent
import com.example.income.presentation.IncomeScreen
import com.example.income.presentation.IncomeViewModel
import com.example.ui.navigation.IncomeRoute

fun NavGraphBuilder.incomeDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<IncomeRoute>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        val component = DaggerIncomeComponent
            .factory()
            .create(appComponent)

        val viewModel: IncomeViewModel = viewModel(factory = component.viewModelFactory)

        IncomeScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}