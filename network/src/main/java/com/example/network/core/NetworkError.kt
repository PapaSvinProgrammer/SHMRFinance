package com.example.network.core

enum class NetworkError: Error {
    NO_INTERNET,
    REQUEST_TIMEOUT,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}