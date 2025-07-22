package com.example.pincodescreen.presentation.navigatoin

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.corecomponent.AppComponent
import com.example.pincodescreen.di.DaggerOtpComponent
import com.example.pincodescreen.presentation.OtpScreen
import com.example.pincodescreen.presentation.OtpViewModel
import com.example.ui.navigation.OtpRoute

fun NavGraphBuilder.otpDestination(
    navController: NavController,
    appComponent: AppComponent
) {
    composable<OtpRoute> {
        val component = remember {
            DaggerOtpComponent
                .factory()
                .create(appComponent)
        }

        val viewModel: OtpViewModel = viewModel(
            factory = component.viewModelFactory
        )

        OtpScreen(
            navController = navController,
            viewModel = viewModel,
            isCreate = true
        )
    }
}