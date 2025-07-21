package com.example.createbankaccount.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.createbankaccount.di.DaggerCreateBankAccountComponent
import com.example.createbankaccount.presentation.CreateBankAccountScreen
import com.example.createbankaccount.presentation.CreateBankAccountViewModel
import com.example.ui.navigation.CreateBankAccountRoute

fun NavGraphBuilder.createBankAccountDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<CreateBankAccountRoute> {
        val component = DaggerCreateBankAccountComponent
            .factory()
            .create(appComponent)

        val viewModel: CreateBankAccountViewModel = viewModel(
            factory = component.viewModelFactory
        )

        CreateBankAccountScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}