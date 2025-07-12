package com.example.network.internal.common

import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.Sender
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.coroutines.delay


suspend fun Sender.reRequest(requestBuilder: HttpRequestBuilder): HttpClientCall {
    var count = 0
    var call = execute(requestBuilder)

    while (count != 3) {
        delay(2000)
        count++

        if (call.response.status.value in 500..599) {
            call = execute(requestBuilder)
        }
        else {
            return call
        }
    }

    return call
}
