package com.example.syncworker.internal.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class WorkerKey(val value: KClass<out ListenableWorker>)
