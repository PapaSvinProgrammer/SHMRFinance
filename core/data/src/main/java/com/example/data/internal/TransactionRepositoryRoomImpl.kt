package com.example.data.internal

import android.util.Log
import com.example.data.external.TransactionRepositoryRoom
import com.example.room.transaction.TransactionDao
import javax.inject.Inject

class TransactionRepositoryRoomImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepositoryRoom {
    override fun create() {
        Log.d("RRRR", dao.toString())
    }
}
