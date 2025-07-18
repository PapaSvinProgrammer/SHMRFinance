package com.example.utils.format

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

    fun formatTimeToHHmm(timeString: String): String {
        val (hoursPart, minutesPart) = timeString.split(":")

        val hours = hoursPart.toInt()
        val minutes = minutesPart.toInt()

        return "%02d:%02d".format(hours, minutes)
    }
}