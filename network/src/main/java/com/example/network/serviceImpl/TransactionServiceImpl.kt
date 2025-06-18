package com.example.network.serviceImpl

import com.example.network.model.Transaction
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.model.TransactionRequest
import com.example.network.serivce.TransactionService
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import jakarta.inject.Inject

class TransactionServiceImpl @Inject constructor(
    private val client: HttpClient
): TransactionService {
    override suspend fun create(request: TransactionRequest): Operation<Transaction, NetworkError> {
        return safeCall {
            client.post("v1/transactions") {
                setBody(request)
            }
        }
    }

    override suspend fun update(
        id: Int,
        request: TransactionRequest
    ): Operation<Transaction, NetworkError> {
        return safeCall {
            client.put("v1/transactions/$id") {
                setBody(request)
            }
        }
    }

    override suspend fun delete(id: Int): Operation<Unit, NetworkError> {
        return safeCall {
            client.delete("v1/transactions/$id")
        }
    }

    override suspend fun getById(id: Int): Operation<Transaction, NetworkError> {
        return safeCall {
            client.get("v1/transactions/$id")
        }
    }

    override suspend fun getByPeriod(
        accountId: Int,
        startDate: String,
        endDate: String
    ): Operation<List<Transaction>, NetworkError> {
        return safeCall {
            client.get("v1/transactions/account/$accountId/period?") {
                url {
                    parameters.append("startDate", startDate)
                    parameters.append("endDate", endDate)
                }
            }
        }
    }
}