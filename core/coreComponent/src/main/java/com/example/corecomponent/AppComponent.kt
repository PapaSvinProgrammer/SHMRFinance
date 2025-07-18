package com.example.corecomponent

import android.content.Context
import com.example.data.internal.di.DataModule
import com.example.network.internal.di.NetworkModule
import com.example.room.internal.di.RoomModule
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
        WorkManagerModule::class
    ]
)
@ApplicationScope
interface AppComponent: RepositoryDependency, WorkManagerDependency {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
