package com.example.settings.utils

import android.content.Context
import android.content.pm.PackageManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun Context.getLastUpdateDate(): String {
    return try {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val lastUpdateTime = packageInfo.lastUpdateTime
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        dateFormat.format(Date(lastUpdateTime))
    } catch (_: PackageManager.NameNotFoundException) {
        UNKNOWN
    }
}

private const val UNKNOWN =  "Неизвестно"