package com.example.corecomponent

import android.content.Context
import androidx.work.WorkManager
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
        RoomModule::class,
        WorkManagerModule::class
    ]
)
@ApplicationScope
interface AppComponent: RepositoryDependency {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val workManager: WorkManager
}
