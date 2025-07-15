package com.example.room.internal.component.bankAccount.update

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "update_bank_account")
internal data class UpdateBankAccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "bank_account_id") val bankAccountId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "balance") val balance: String,
    @ColumnInfo(name = "currency") val currency: String
)