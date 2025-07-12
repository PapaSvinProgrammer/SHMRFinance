package com.example.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out T>(private val dispatcher: CoroutineDispatcher) {
    suspend fun execute(params: Params): T = withContext(dispatcher) {
        run(params)
    }

    protected abstract suspend fun run(params: Params): T
}
