package com.example.common

suspend inline fun <reified T> request(
    block: () -> Result<T>
): Result<T> {
    var refresh = false
    var result: Result<T> = Result.failure(UnknownException())

    val response = block()

    response.onSuccess {
        result = Result.success(it)
    }.onFailure {
        if (it is ServerErrorException) {
            refresh = true
        }
    }

    if (refresh) {
        return reRequest { block() }
    }

    return result
}