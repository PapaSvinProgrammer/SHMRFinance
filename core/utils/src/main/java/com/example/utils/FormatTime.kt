package com.example.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FormatTime {
    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }
}