package com.example.network.internal.common

import com.example.shmrfinance.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object HttpClientFactory {
    fun <T : HttpClientEngineConfig> create(engine: HttpClientEngineFactory<T>): HttpClient {
        val client =  HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                url("https://shmr-finance.ru/api/")
                header("accept", "application/json")
                bearerAuth(BuildConfig.API_KEY)
            }
        }

        client.plugin(HttpSend).intercept { request ->
            val call = execute(request)

            if (call.response.status.value in 500..599) {
                reRequest(request)
            }
            else {
                call
            }
        }

        return client
    }
}
