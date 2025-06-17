package com.example.network.serviceImpl

import com.example.network.model.BankAccount
import com.example.network.model.BankAccountHistoryResponse
import com.example.network.core.NetworkError
import com.example.network.core.Operation
import com.example.network.core.safeCall
import com.example.network.model.AccountRequest
import com.example.network.serivce.BankAccountService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import jakarta.inject.Inject

class BankAccountServiceImpl @Inject constructor(
    private val client: HttpClient
): BankAccountService {
    override suspend fun getAll(): Operation<List<BankAccount>, NetworkError> {
        return safeCall {
            client.get("v1/accounts")
        }
    }

    override suspend fun getById(id: Int): Operation<BankAccount, NetworkError> {
        return safeCall {
            client.get("v1/accounts/$id")
        }
    }

    override suspend fun create(request: AccountRequest): Operation<BankAccount, NetworkError> {
        return safeCall {
            client.post("v1/accounts") {
                setBody(request)
            }
        }
    }

    override suspend fun update(
        id: Int,
        request: AccountRequest
    ): Operation<BankAccount, NetworkError> {
        return safeCall {
            client.put("v1/accounts/$id") {
                setBody(request)
            }
        }
    }

    override suspend fun getUpdateHistory(id: Int): Operation<BankAccountHistoryResponse, NetworkError> {
        return safeCall {
            client.get("v1/accounts/$id/history")
        }
    }
}