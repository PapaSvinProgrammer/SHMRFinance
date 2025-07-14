package com.example.data.internal.local

import android.util.Log
import com.example.data.external.local.TransactionRepositoryRoom
import com.example.room.internal.component.transaction.TransactionDao
import javax.inject.Inject

class TransactionRepositoryRoomImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepositoryRoom {
    override fun create() {
        Log.d("RRRR", dao.toString())
    }
}