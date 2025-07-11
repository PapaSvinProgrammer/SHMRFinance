package com.example.network.internal.service

import com.example.network.internal.model.TransactionDto
import com.example.model.Transaction
import com.example.model.TransactionRequest
import com.example.model.TransactionResponse
import com.example.network.internal.common.safeCall
import com.example.network.external.TransactionService
import com.example.network.internal.mapper.toDomain
import com.example.network.internal.mapper.toDto
import com.example.network.internal.model.TransactionResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject

internal class TransactionServiceImpl @Inject constructor(
    private val client: HttpClient
) : TransactionService {
    override suspend fun create(request: TransactionRequest): Result<TransactionResponse> {
        return safeCall<TransactionResponseDto> {
            client.post("v1/transactions") {
                setBody(request.toDto())
            }
        }.map { it.toDomain() }
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Result<Transaction> {
        return safeCall<TransactionDto> {
            client.put("v1/transactions/$id") {
                setBody(request.toDto())
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