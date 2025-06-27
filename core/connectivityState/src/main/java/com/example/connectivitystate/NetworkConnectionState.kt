package com.example.connectivitystate

sealed interface NetworkConnectionState {
    data object Success : NetworkConnectionState
    data object Failure : NetworkConnectionState
}