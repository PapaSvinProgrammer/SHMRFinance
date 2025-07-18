package com.example.synchronizationscreen.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal object PrettyString {
    fun millisToString(millis: Long): String {
        val instant = Instant.ofEpochMilli(millis)
        val formatter = DateTimeFormatter.ofPattern("dd MMMM HH:mm")
            .withZone(ZoneId.systemDefault())

        return formatter.format(instant)
    }
}