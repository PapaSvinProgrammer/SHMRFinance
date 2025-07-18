package com.example.synchronizationscreen.utils

import com.example.syncworker.external.WorkerNames

internal fun String.toNormalName(): String {
    return when (this) {
        WorkerNames.CATEGORY_NAME -> "Категории"
        WorkerNames.BANK_ACCOUNT_NAME -> "Счета"
        WorkerNames.TRANSACTION_NAME -> "Транзакции"
        else -> "Неизвестно"
    }
}