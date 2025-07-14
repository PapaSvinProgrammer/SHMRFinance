package com.example.corecomponent

import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.local.CategoryRepositoryRoom
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.PreferencesRepository
import com.example.data.external.remote.TransactionRepository

interface RepositoryDependency {
    val categoryRepository: CategoryRepository
    val transactionRepository: TransactionRepository
    val bankAccountRepository: BankAccountRepository
    val preferencesRepository: PreferencesRepository

    val categoryRepositoryRoom: CategoryRepositoryRoom
    val bankAccountRepositoryRoom: BankAccountRepositoryRoom
    val transactionRepositoryRoom: TransactionRepositoryRoom
}