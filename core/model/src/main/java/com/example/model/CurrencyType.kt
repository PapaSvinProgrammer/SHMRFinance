package com.example.model

enum class CurrencyType {
    RUB,
    USD,
    EUR
}

fun CurrencyType.toSlug(): String {
    return when (this) {
        CurrencyType.RUB -> "RUB"
        CurrencyType.USD -> "USD"
        CurrencyType.EUR -> "EUR"
    }
}