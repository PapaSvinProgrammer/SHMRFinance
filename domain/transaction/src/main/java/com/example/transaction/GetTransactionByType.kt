package com.example.transaction

import com.example.utils.UnknownException
import com.example.data.external.remote.TransactionRepository
import com.example.model.Transaction
import com.example.transaction.model.GetTransactionParams
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetTransactionByType @Inject constructor(
    private val transactionRepository: TransactionRepository
) : UseCase<GetTransactionParams, Result<List<Transaction>>>(Dispatchers.IO) {
    override suspend fun run(params: GetTransactionParams): Result<List<Transaction>> {
        val response = transactionRepository.getByPeriod(
            accountId = params.accountId,
            startDate = params.startDate,
            endDate = params.endDate
        )

        var filterResult: Result<List<Transaction>> = Result.failure(UnknownException())

        response.onSuccess { data ->
            val res = data.filter {
                it.category.isIncome == params.isIncome
            }.sortedByDescending { it.transactionDate }
            filterResult = Result.success(res)
        }.onFailure {
            filterResult = Result.failure(it)
        }

        return filterResult
    }
}