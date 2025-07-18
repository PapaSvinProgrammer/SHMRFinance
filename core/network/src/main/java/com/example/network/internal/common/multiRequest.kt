package com.example.network.internal.common

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <Key, Param> multiRequest(
    list: List<Key>,
    execute: suspend (Key) -> Result<Param>
): List<Result<Param>> {
    val tasks = mutableListOf<Deferred<Result<Param>>>()

    list.forEach { id ->
        val task = coroutineScope {
            async { execute(id) }
        }

        tasks.add(task)
    }

    val result = mutableListOf<Result<Param>>()

    tasks.awaitAll().forEach {
        result.add(it)
    }

    return result
}