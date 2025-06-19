package com.example.domain.useCase

import com.example.common.NetworkError
import com.example.common.Result
import com.example.common.request
import com.example.data.repository.TransactionRepository
import com.example.model.Transaction
import jakarta.inject.Inject

class GetTransaction @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend fun getById(id: Int): Result<Transaction, NetworkError> {
        return request { transactionRepository.getById(id) }
    }

    suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>, NetworkError> {
        return request {
            transactionRepository.getByPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }
    }

    suspend fun getTransactionsType(
        isIncome: Boolean = false,
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>, NetworkError> {
        val response = request {
            transactionRepository.getByPeriod(
                accountId = accountId,
                startDate = startDate,
                endDate = endDate
            )
        }

        var filterResult: Result<List<Transaction>, NetworkError> = Result.Error(NetworkError.UNKNOWN)

        response.onSuccess { data ->
            val res = data.filter { it.category.isIncome == isIncome }
            filterResult = Result.Success(res)
        }.onError {
            filterResult = Result.Error(it)
        }

        return filterResult
    }
}