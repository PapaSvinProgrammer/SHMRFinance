package com.example.transactionhistory.di

import android.content.Context
import com.example.transactionhistory.TransactionHistoryViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [TransactionModule::class]
)
@TransactionScope
interface TransactionComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TransactionComponent
    }

    val viewModel: TransactionHistoryViewModel
}