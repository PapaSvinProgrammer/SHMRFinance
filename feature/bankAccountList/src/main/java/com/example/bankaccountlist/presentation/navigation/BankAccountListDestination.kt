package com.example.bankaccountlist.presentation.navigation

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.bankaccountlist.di.DaggerBankAccountListComponent
import com.example.bankaccountlist.presentation.BankAccountListScreen
import com.example.bankaccountlist.presentation.BankAccountListViewModel
import com.example.corecomponent.AppComponent
import com.example.ui.navigation.BankAccountListRoute

fun NavGraphBuilder.bankAccountListDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<BankAccountListRoute> {
        val component = remember {
            DaggerBankAccountListComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: BankAccountListViewModel = viewModel(
            factory = component.viewModelFactory
        )

        BankAccountListScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}