package com.example.syncworker.external

import com.example.syncworker.internal.SyncBankAccountWorker
import com.example.syncworker.internal.SyncCategoryWorker
import com.example.syncworker.internal.SyncTransactionWorker

object WorkerNames {
    const val BANK_ACCOUNT_NAME = SyncBankAccountWorker.NAME
    const val CATEGORY_NAME = SyncCategoryWorker.NAME
    const val TRANSACTION_NAME = SyncTransactionWorker.NAME
}