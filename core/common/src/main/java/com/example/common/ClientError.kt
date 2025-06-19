package com.example.common

enum class ClientError: RootError {
    ACCOUNT_ID
}

fun ClientError.toSlug(): String {
    return "Ошибка. Выберете основной счет"
}