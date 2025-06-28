package com.example.shmrfinance.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articles.ArticlesViewModel
import com.example.bankaccountlist.BankAccountListViewModel
import com.example.bankaccountscreen.BankAccountViewModel
import com.example.createbankaccount.CreateBankAccountViewModel
import com.example.expenses.ExpensesViewModel
import com.example.income.IncomeViewModel
import com.example.transactionhistory.TransactionHistoryViewModel
import com.example.updatebankaccount.UpdateBankAccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    fun bindsArticleViewModel(viewModel: ArticlesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BankAccountListViewModel::class)
    fun bindsBankAccountListViewModel(viewModel: BankAccountListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BankAccountViewModel::class)
    fun bindsBankAccountViewModel(viewModel: BankAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateBankAccountViewModel::class)
    fun bindsCreateBankAccountViewModel(viewModel: CreateBankAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindsExpensesViewModel(viewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    fun bindsIncomeViewModel(viewModel: IncomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionHistoryViewModel::class)
    fun bindsTransactionViewModel(viewModel: TransactionHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateBankAccountViewModel::class)
    fun bindsUpdateBankAccountViewModel(viewMode: UpdateBankAccountViewModel): ViewModel
}