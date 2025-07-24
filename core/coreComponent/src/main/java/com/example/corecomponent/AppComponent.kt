package com.example.corecomponent

import android.content.Context
import com.example.data.internal.di.DataModule
import com.example.network.internal.di.NetworkModule
import com.example.room.internal.di.RoomModule
import com.example.security.internal.di.SecurityDependency
import com.example.security.internal.di.SecurityModule
import com.example.syncworker.external.WorkManagerModule
import com.example.syncworker.external.WorkManagerDependency
import com.example.utils.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        NetworkModule::class,
        RoomModule::class,
        WorkManagerModule::class,
        AppModule::class,
        SecurityModule::class
    ]
)
@ApplicationScope
interface AppComponent : RepositoryDependency, WorkManagerDependency, SecurityDependency {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val viewModel: AppViewModel
}
