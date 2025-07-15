package com.example.room.internal.component.transaction

import androidx.room.Embedded
import androidx.room.Relation
import com.example.room.internal.component.bankAccount.BankAccountEntity
import com.example.room.internal.component.category.CategoryEntity

internal data class TransactionResult(
    @Embedded val transaction: TransactionEntity,
    @Relation(
        parentColumn = "account_id",
        entityColumn = "bank_account_id"
    )
    val bankAccount: BankAccountEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "category_id"
    )
    val category: CategoryEntity
)
