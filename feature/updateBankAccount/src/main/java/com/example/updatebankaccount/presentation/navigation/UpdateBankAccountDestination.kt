package com.example.updatebankaccount.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.corecomponent.AppComponent
import com.example.ui.navigation.UpdateBankAccountRoute
import com.example.updatebankaccount.di.DaggerUpdateBankAccountComponent
import com.example.updatebankaccount.presentation.UpdateBankAccountScreen
import com.example.updatebankaccount.presentation.UpdateBankAccountViewModel

fun NavGraphBuilder.updateBankAccountDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<UpdateBankAccountRoute> {
        val route = it.toRoute<UpdateBankAccountRoute>()
        val component = DaggerUpdateBankAccountComponent
            .factory()
            .create(appComponent)

        val viewModel: UpdateBankAccountViewModel = viewModel(
            factory = component.viewModelFactory
        )

        UpdateBankAccountScreen(
            navController = navController,
            bankAccountId = route.id,
            viewModel = viewModel
        )
    }
}