package com.example.utils

import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object FormatTime {
    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }

    fun convertTimeFromISO(inputDate: String): String {
        val formatterInput = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val formatterOutput = DateTimeFormatter.ofPattern("HH:mm")
        return ZonedDateTime.parse(inputDate, formatterInput).format(formatterOutput)
    }
}