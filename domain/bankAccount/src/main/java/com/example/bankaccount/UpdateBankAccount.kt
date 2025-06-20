package com.example.bankaccount

import com.example.common.request
import com.example.data.repository.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import jakarta.inject.Inject

class UpdateBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(
        id: Int,
        accountRequest: AccountRequest
    ): Result<BankAccount> {
        return request {
            bankAccountRepository.update(
                id = id,
                request = accountRequest
            )
        }
    }
}