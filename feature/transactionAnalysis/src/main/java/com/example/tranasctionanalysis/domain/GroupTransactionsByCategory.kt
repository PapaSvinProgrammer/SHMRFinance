package com.example.tranasctionanalysis.domain

import com.example.model.Transaction
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class GroupTransactionsByCategory @Inject constructor(
): UseCase<List<Transaction>, List<Transaction>>(
    Dispatchers.Default
) {
    override suspend fun run(params: List<Transaction>): List<Transaction> {
        val groupTransactions = params.groupBy { it.category.id }
        val result = mutableListOf<Transaction>()

        for ((_, value) in groupTransactions) {
            val amount = value.sumOf { it.amount }
            val tempTransaction = value[0].copy(amount = amount)
            result.add(tempTransaction)
        }

        return result.sortedByDescending { it.amount }
    }
}