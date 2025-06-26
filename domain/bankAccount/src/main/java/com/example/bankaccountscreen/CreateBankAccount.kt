package com.example.bankaccountscreen

import com.example.common.request
import com.example.data.repository.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import javax.inject.Inject

class CreateBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(accountRequest: AccountRequest): Result<BankAccount> {
        return request { bankAccountRepository.create(accountRequest) }
    }
}