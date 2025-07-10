package com.example.shmrfinance.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.articles.presentation.ArticlesScreen
import com.example.articles.presentation.ArticlesViewModel
import com.example.articles.di.DaggerArticlesComponent
import com.example.bankaccountlist.presentation.BankAccountListScreen
import com.example.bankaccountlist.presentation.BankAccountListViewModel
import com.example.bankaccountlist.di.DaggerBankAccountListComponent
import com.example.bankaccountscreen.presentation.BankAccountScreen
import com.example.bankaccountscreen.presentation.BankAccountViewModel
import com.example.bankaccountscreen.di.DaggerBankAccountComponent
import com.example.createbankaccount.presentation.CreateBankAccountScreen
import com.example.createbankaccount.presentation.CreateBankAccountViewModel
import com.example.createbankaccount.di.DaggerCreateBankAccountComponent
import com.example.createtransaction.presentation.CreateTransactionScreen
import com.example.createtransaction.presentation.CreateTransactionViewModel
import com.example.createtransaction.di.DaggerCreateTransactionComponent
import com.example.expenses.presentation.ExpensesScreen
import com.example.expenses.presentation.ExpensesViewModel
import com.example.expenses.di.DaggerExpensesComponent
import com.example.income.presentation.IncomeScreen
import com.example.income.presentation.IncomeViewModel
import com.example.income.di.DaggerIncomeComponent
import com.example.navigationroute.ArticlesRoute
import com.example.navigationroute.BankAccountListRoute
import com.example.navigationroute.BankAccountRoute
import com.example.navigationroute.CreateBankAccountRoute
import com.example.navigationroute.CreateTransactionRoute
import com.example.navigationroute.ExpensesRoute
import com.example.navigationroute.IncomeRoute
import com.example.navigationroute.NavRoute
import com.example.navigationroute.SettingsRoute
import com.example.navigationroute.SplashRoute
import com.example.navigationroute.TransactionHistoryRoute
import com.example.navigationroute.UpdateBankAccountRoute
import com.example.navigationroute.UpdateTransactionRoute
import com.example.settings.SettingsScreen
import com.example.splash.SplashScreen
import com.example.transactionhistory.presentation.TransactionHistoryScreen
import com.example.transactionhistory.presentation.TransactionHistoryViewModel
import com.example.transactionhistory.di.DaggerTransactionComponent
import com.example.updatebankaccount.presentation.UpdateBankAccountScreen
import com.example.updatebankaccount.presentation.UpdateBankAccountViewModel
import com.example.updatebankaccount.di.DaggerUpdateBankAccountComponent
import com.example.updatetransaction.di.DaggerUpdateTransactionComponent
import com.example.updatetransaction.presentation.UpdateTransactionScreen
import com.example.updatetransaction.presentation.UpdateTransactionViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startRoute: NavRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ExpensesRoute,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        }
    ) {
        composable<SplashRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            SplashScreen(navController = navController)
        }

        composable<ExpensesRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerExpensesComponent
                .factory()
                .create(LocalContext.current)
            val viewModel: ExpensesViewModel = viewModel(factory = component.viewModelFactory)

            ExpensesScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<IncomeRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerIncomeComponent
                .factory()
                .create(LocalContext.current)
            val viewModel: IncomeViewModel = viewModel(factory = component.viewModelFactory)

            IncomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<BankAccountRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerBankAccountComponent
                .factory()
                .create(LocalContext.current)
            val viewModel: BankAccountViewModel = viewModel(factory = component.viewModelFactory)

            BankAccountScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<ArticlesRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            val component = DaggerArticlesComponent
                .factory()
                .create(LocalContext.current)
            val viewModel: ArticlesViewModel = viewModel(factory = component.viewModelFactory)

            ArticlesScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<SettingsRoute>(
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) },
            popEnterTransition = { fadeIn(animationSpec = tween(500)) },
            popExitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            SettingsScreen()
        }

        composable<CreateBankAccountRoute> {
            val component = DaggerCreateBankAccountComponent
                .factory()
                .create(LocalContext.current)
            val viewModel: CreateBankAccountViewModel = viewModel(
                factory = component.viewModelFactory
            )

            CreateBankAccountScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<BankAccountListRoute> {
            val component = DaggerBankAccountListComponent
                .factory()
                .create(LocalContext.current)
            val viewModel: BankAccountListViewModel = viewModel(
                factory = component.viewModelFactory
            )

            BankAccountListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<TransactionHistoryRoute> {
            val route = it.toRoute<TransactionHistoryRoute>()
            val component = DaggerTransactionComponent
                .factory()
                .create(LocalContext.current)

            val viewModel: TransactionHistoryViewModel = viewModel(
                factory = component.viewModelFactory
            )

            TransactionHistoryScreen(
                navController = navController,
                isIncome = route.isIncome,
                viewModel = viewModel
            )
        }

        composable<UpdateBankAccountRoute> {
            val route = it.toRoute<UpdateBankAccountRoute>()
            val component = DaggerUpdateBankAccountComponent
                .factory()
                .create()
            val viewModel: UpdateBankAccountViewModel = viewModel(
                factory = component.viewModelFactory
            )

            UpdateBankAccountScreen(
                navController = navController,
                bankAccountId = route.id,
                viewModel = viewModel
            )
        }

        composable<CreateTransactionRoute> {
            val route = it.toRoute<CreateTransactionRoute>()
            val component = DaggerCreateTransactionComponent
                .factory()
                .create(LocalContext.current)

            val viewModel: CreateTransactionViewModel = viewModel(
                factory = component.viewModelFactory
            )

            CreateTransactionScreen(
                navController = navController,
                viewModel = viewModel,
                isIncome = route.isIncome
            )
        }

        composable<UpdateTransactionRoute> {
            val route = it.toRoute<UpdateTransactionRoute>()
            val component = DaggerUpdateTransactionComponent
                .factory()
                .create()

            val viewModel: UpdateTransactionViewModel = viewModel(
                factory = component.viewModelFactory
            )

            UpdateTransactionScreen(
                navController = navController,
                viewModel = viewModel,
                transactionId = route.id
            )
        }
    }
}
