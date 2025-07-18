package com.example.syncworker.external

import com.example.syncworker.internal.di.WorkManagerModuleImpl
import dagger.Module

@Module(includes = [WorkManagerModuleImpl::class])
interface WorkManagerModule