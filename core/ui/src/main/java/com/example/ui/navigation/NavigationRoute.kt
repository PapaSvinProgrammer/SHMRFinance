package com.example.ui.navigation

import kotlinx.serialization.Serializable

sealed interface NavRoute

@Serializable
data class SplashRoute(
    val isOtp: Boolean
) : NavRoute

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

@Serializable
data class TransactionAnalysisRoute(
    val isIncome: Boolean
) : NavRoute

@Serializable
data object SynchronizationRoute : NavRoute

@Serializable
data object ColorSelectorRoute : NavRoute

@Serializable
data object LanguageSelectorRoute: NavRoute

@Serializable
data object AboutRoute : NavRoute

@Serializable
data class OtpRoute(
    val isCreate: Boolean,
    val isDisable: Boolean
) : NavRoute

@Serializable
data object SettingsOtpRoute : NavRoute

@Serializable
data object HapticSettingsRoute : NavRoute