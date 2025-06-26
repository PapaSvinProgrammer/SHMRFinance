package com.example.network.serviceImpl

import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.model.BankAccountHistoryResponse
import com.example.network.common.safeCall
import com.example.network.mapper.toDomain
import com.example.network.mapper.toDto
import com.example.network.model.BankAccountDto
import com.example.network.model.BankAccountHistoryResponseDto
import com.example.network.serivce.BankAccountService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import javax.inject.Inject

class BankAccountServiceImpl @Inject constructor(
    private val client: HttpClient
): BankAccountService {
    override suspend fun getAll(): Result<List<BankAccount>> {
        return safeCall<List<BankAccountDto>> {
            client.get("v1/accounts")
        }.map { it.toDomain() }
    }

    override suspend fun getById(id: Int): Result<BankAccount> {
        return safeCall<BankAccountDto> {
            client.get("v1/accounts/$id")
        }.map { it.toDomain() }
    }

    override suspend fun create(request: AccountRequest): Result<BankAccount> {
        return safeCall<BankAccountDto> {
            client.post("v1/accounts") {
                setBody(request.toDto())
            }
        }.map { it.toDomain() }
    }

    override suspend fun update(
        id: Int,
        request: AccountRequest
    ): Result<BankAccount> {
        return safeCall<BankAccountDto> {
            client.put("v1/accounts/$id") {
                setBody(request.toDto())
            }
        }.map { it.toDomain() }
    }

    override suspend fun delete(id: Int): Result<Unit> {
        return safeCall {
            client.get("v1/accounts/$id")
        }
    }

    override suspend fun getUpdateHistory(id: Int): Result<BankAccountHistoryResponse> {
        return safeCall<BankAccountHistoryResponseDto> {
            client.get("v1/accounts/$id/history")
        }.map { it.toDomain() }
    }
}