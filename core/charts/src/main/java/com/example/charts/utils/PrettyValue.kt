package com.example.charts.utils

import java.math.BigDecimal

internal object PrettyValue {
    fun getPrettyColumnValue(amount: BigDecimal): String {
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
}