package com.example.shmrfinance.navigation

import com.example.shmrfinance.R


sealed class BottomBarTab(
    val title: Int,
    val icon: Int,
    val route: NavRoute
) {
    data object ExpensesTab: BottomBarTab(
        title = R.string.expenses,
        icon = R.drawable.ic_expense,
        route = ExpensesRoute
    )

    data object IncomeTab: BottomBarTab(
        title = R.string.incomes,
        icon = R.drawable.ic_income,
        route = IncomeRoute
    )

    data object BankAccountTab: BottomBarTab(
        title = R.string.bank_account,
        icon = R.drawable.ic_bank_account,
        route = BankAccountRoute
    )

    data object ArticlesTab: BottomBarTab(
        title = R.string.articles,
        icon = R.drawable.ic_article,
        route = ArticlesRoute
    )

    data object SettingsTab: BottomBarTab(
        title = R.string.settings,
        icon = R.drawable.ic_settings,
        route = SettingsRoute
    )
}

object BottomBarItems {
    val items = listOf(
        BottomBarTab.ExpensesTab,
        BottomBarTab.IncomeTab,
        BottomBarTab.BankAccountTab,
        BottomBarTab.ArticlesTab,
        BottomBarTab.SettingsTab
    )
}