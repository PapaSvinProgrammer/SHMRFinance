package com.example.bankaccountscreen.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.bankaccountscreen.di.DaggerBankAccountComponent
import com.example.bankaccountscreen.presentation.BankAccountScreen
import com.example.bankaccountscreen.presentation.BankAccountViewModel
import com.example.corecomponent.AppComponent
import com.example.ui.navigation.BankAccountRoute

fun NavGraphBuilder.bankAccountDestination(
    appComponent: AppComponent,
    navController: NavController
) {
    composable<BankAccountRoute>(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) }
    ) {
        val component = DaggerBankAccountComponent
            .factory()
            .create(
                context = LocalContext.current,
                appComponent = appComponent
            )

        val viewModel: BankAccountViewModel = viewModel(factory = component.viewModelFactory)

        BankAccountScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}