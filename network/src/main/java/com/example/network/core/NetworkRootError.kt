package com.example.network.core

enum class NetworkRootError: RootError {
    NO_INTERNET,
    REQUEST_TIMEOUT,
    SUCCESS_DELETE_TRANSACTION,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}