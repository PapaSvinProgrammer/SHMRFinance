package com.example.room.internal.component.bankAccount

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "bank_account",
    indices = [
        Index(
            value = ["bank_account_id"],
            unique = true
        )
    ]
)
internal data class BankAccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "bank_account_id")
    val accountId: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "balance")
    val balance: String,
    @ColumnInfo(name = "currency")
    val currency: String
)