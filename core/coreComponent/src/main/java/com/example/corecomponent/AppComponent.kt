package com.example.corecomponent

import android.content.Context
import com.example.data.external.remote.BankAccountRepository
import com.example.data.external.remote.CategoryRepository
import com.example.data.external.remote.PreferencesRepository
import com.example.data.external.remote.TransactionRepository
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.data.internal.di.DataModule
import com.example.network.internal.di.NetworkModule
import com.example.room.internal.di.RoomModule
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
