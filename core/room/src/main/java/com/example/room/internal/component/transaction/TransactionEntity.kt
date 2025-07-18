package com.example.room.internal.component.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bank_transaction"
)
internal data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id")
    val transactionId: Int,
    @ColumnInfo(name = "account_id")
    val accountId: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "transaction_date")
    val transactionDate: Long,
    @ColumnInfo(name = "comment")
    val comment: String?,
    @ColumnInfo(name = "is_create")
    val isCreate: Boolean,
    @ColumnInfo(name = "is_update")
    val isUpdate: Boolean,
    @ColumnInfo(name = "is_delete")
    val isDelete: Boolean,
)