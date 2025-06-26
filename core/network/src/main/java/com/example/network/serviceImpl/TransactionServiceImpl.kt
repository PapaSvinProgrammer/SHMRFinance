package com.example.network.serviceImpl

import com.example.network.model.TransactionDto
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.network.common.safeCall
import com.example.network.mapper.toDomain
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject

class TransactionServiceImpl @Inject constructor(
    private val client: HttpClient
): com.example.network.serivce.TransactionService {
    override suspend fun create(request: TransactionRequest): Result<Transaction> {
        return safeCall<TransactionDto> {
            client.post("v1/transactions") {
                setBody(request)
            }
        }.map { it.toDomain() }
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        return safeCall<TransactionDto> {
            client.put("v1/transactions/$id") {
                setBody(request)
            }
        }.map { it.toDomain() }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall {
            client.delete("v1/transactions/$id")
        }
    }

    override suspend fun getById(id: Int): Result<Transaction> {
        return safeCall<TransactionDto> {
            client.get("v1/transactions/$id")
        }.map { it.toDomain() }
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Result<List<Transaction>> {
        return safeCall<List<TransactionDto>> {
            client.get("v1/transactions/account/$accountId/period?") {
                url {
                    parameters.append("startDate", startDate)
                    parameters.append("endDate", endDate)
                }
            }
        }.map { response ->
            response.map { it.toDomain() }
        }
    }
}