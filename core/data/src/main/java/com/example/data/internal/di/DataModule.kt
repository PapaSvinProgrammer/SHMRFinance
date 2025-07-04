package com.example.data.internal.di

import com.example.network.external.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        DataModuleImpl::class,
        NetworkModule::class
    ]
)
interface DataModule