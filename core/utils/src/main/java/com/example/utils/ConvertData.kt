package com.example.utils

object ConvertData {
    fun getPrettyInt(amount: Float): String {
        val str = amount.toInt().toString()
        val res = StringBuilder()
        var count = 0

        for (i in str.lastIndex downTo 0) {
            if (count == 3 && str[i] != '-') {
                res.append(" ")
                count = 0
            }

            count++
            res.append(str[i])
        }

        return res.reversed().toString()
    }

    fun getCurrencySymbol(currency: String): String {
        return when (currency) {
            "RUB" -> "₽"
            "USD" -> "$"
            "EUR" -> "€"
            else -> ""
        }
    }

    fun createPrettyAmount(amount: Float, currency: String): String {
        val prettyInt = getPrettyInt(amount)
        val symbol = getCurrencySymbol(currency)
        return "$prettyInt $symbol"
    }
}