package com.example.corecomponent

import android.content.Context
import com.example.data.external.BankAccountRepository
import com.example.data.external.CategoryRepository
import com.example.data.external.PreferencesRepository
import com.example.data.external.TransactionRepository
import com.example.data.external.TransactionRepositoryRoom
import com.example.data.internal.di.DataModule
import com.example.network.internal.di.NetworkModule
import com.example.room.di.RoomModule
import com.example.utils.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        NetworkModule::class,
        RoomModule::class
    ]
)
@ApplicationScope
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val categoryRepository: CategoryRepository
    val transactionRepository: TransactionRepository
    val bankAccountRepository: BankAccountRepository
    val transactionRepositoryRoom: TransactionRepositoryRoom
    val preferencesRepository: PreferencesRepository
}
