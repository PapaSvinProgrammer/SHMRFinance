package com.example.navigationroute

import kotlinx.serialization.Serializable

sealed interface NavRoute

@Serializable
data object SplashRoute: NavRoute

@Serializable
data object ExpensesRoute: NavRoute

@Serializable
data object IncomeRoute: NavRoute

@Serializable
data object ArticlesRoute: NavRoute

@Serializable
data object BankAccountRoute: NavRoute

@Serializable
data object SettingsRoute: NavRoute

@Serializable
data object CreateBankAccountRoute: NavRoute

@Serializable
data object BankAccountListScreenRoute: NavRoute

@Serializable
data class TransactionHistoryRoute(
    val isIncome: Boolean
): NavRoute