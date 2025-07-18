package com.example.expenses.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.expenses.di.DaggerExpensesComponent
import com.example.expenses.presentation.ExpensesScreen
import com.example.expenses.presentation.ExpensesViewModel
import com.example.ui.navigation.ExpensesRoute

fun NavGraphBuilder.expensesDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<ExpensesRoute>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        val component = DaggerExpensesComponent
            .factory()
            .create(
                context = LocalContext.current,
                appComponent = appComponent
            )

        val viewModel: ExpensesViewModel = viewModel(factory = component.viewModelFactory)

        ExpensesScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}