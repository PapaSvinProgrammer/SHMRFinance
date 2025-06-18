package com.example.shmrfinance.app.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FormatDate {
    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }
}