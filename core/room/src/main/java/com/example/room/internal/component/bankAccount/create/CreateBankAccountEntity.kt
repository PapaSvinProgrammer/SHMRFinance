package com.example.room.internal.component.bankAccount.create

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "create_bank_account")
internal data class CreateBankAccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "balance") val balance: String,
    @ColumnInfo(name = "currency") val currency: String
)