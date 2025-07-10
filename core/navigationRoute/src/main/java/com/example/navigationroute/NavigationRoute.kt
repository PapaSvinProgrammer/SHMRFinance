package com.example.navigationroute

import kotlinx.serialization.Serializable

sealed interface NavRoute

@Serializable
data object SplashRoute : NavRoute

@Serializable
data object ExpensesRoute : NavRoute

@Serializable
data object IncomeRoute : NavRoute

@Serializable
data object ArticlesRoute : NavRoute

@Serializable
data object BankAccountRoute : NavRoute

@Serializable
data object SettingsRoute : NavRoute

@Serializable
data object CreateBankAccountRoute : NavRoute

@Serializable
data object BankAccountListRoute : NavRoute

@Serializable
data class TransactionHistoryRoute(
    val isIncome: Boolean
) : NavRoute

@Serializable
data class UpdateBankAccountRoute(
    val id: Int
) : NavRoute

@Serializable
data class CreateTransactionRoute(
    val isIncome: Boolean
) : NavRoute

@Serializable
data class UpdateTransactionRoute(
    val id: Int
) : NavRoute
