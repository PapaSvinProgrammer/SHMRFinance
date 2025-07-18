package com.example.corecomponent

import android.content.Context
import androidx.work.WorkManager
import com.example.utils.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
interface WorkManagerModule {
    companion object {
        @Provides
        @ApplicationScope
        fun providesWorkManager(context: Context): WorkManager {
            return WorkManager.getInstance(context)
        }
    }
}