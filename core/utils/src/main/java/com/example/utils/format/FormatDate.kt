package com.example.utils.format

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object FormatDate {
    fun getPrettyDayAndTime(value: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("d MMMM HH:mm")

        val zonedDateTime = ZonedDateTime.parse(value, inputFormatter)
        return zonedDateTime.format(outputFormatter)
    }

    fun getCurrentMonthDate(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    fun convertStringToMillis(value: String): Long {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date = inputFormat.parse(value)
        return date?.time ?: 0L
    }

    fun getPrettyDate(value: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(value) ?: return ""
        return outputFormat.format(date)
    }

    fun convertMillisToString(value: Long): String {
        val date = Date(value)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(date)
    }

    fun createRequestDate(dateStr: String, timeStr: String): String {
        val date = LocalDate.parse(dateStr)
        val time = LocalTime.parse(timeStr)

        val dateTime = LocalDateTime.of(date, time)

        return dateTime
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"))
    }

    fun convertDateFromISO(inputDate: String): String {
        val zonedDateTime = ZonedDateTime.parse(inputDate)
        return zonedDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    fun isoDateToMillis(date: String): Long {
        return Instant
            .from(DateTimeFormatter.ISO_INSTANT.parse(date))
            .toEpochMilli()
    }

    fun dateToMillis(date: String): Long {
        return LocalDate.parse(date)
            .atStartOfDay()
            .toInstant(ZoneOffset.UTC)
            .toEpochMilli()
    }

    fun millisToIsoDate(millis: Long): String {
        return Instant.ofEpochMilli(millis)
            .atOffset(ZoneOffset.UTC)
            .format(DateTimeFormatter.ISO_INSTANT)
    }
}