package com.example.room.internal.component.bankAccount.delete

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delete_bank_account")
data class DeleteBankAccount(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "bank_account_id") val bankAccountId: Int
)