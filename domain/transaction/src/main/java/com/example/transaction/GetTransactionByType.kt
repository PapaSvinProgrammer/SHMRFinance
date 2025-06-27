package com.example.transaction

import com.example.common.UnknownException
import com.example.common.request
import com.example.data.repository.TransactionRepository
import com.example.model.Transaction
import javax.inject.Inject

class GetTransactionByType @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun execute(
        isIncome: Boolean = false,
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {
        val response = request {
            transactionRepository.getByPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }

        var filterResult: Result<List<Transaction>> = Result.failure(UnknownException())

        response.onSuccess { data ->
            val res = data.filter {
                it.category.isIncome == isIncome
            }.sortedByDescending { it.transactionDate }
            filterResult = Result.success(res)
        }.onFailure {
            filterResult = Result.failure(it)
        }

        return filterResult
    }
}