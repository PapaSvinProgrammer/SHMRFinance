package com.example.network.connectivityState

sealed interface NetworkConnectionState {
    data object Success : NetworkConnectionState
    data object Failure : NetworkConnectionState
}