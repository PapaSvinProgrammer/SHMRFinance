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

fun String.toCurrencyType(): CurrencyType {
    return when (this) {
        "RUB" -> CurrencyType.RUB
        "USD" -> CurrencyType.USD
        "EUR" -> CurrencyType.EUR
        else -> CurrencyType.RUB
    }
}