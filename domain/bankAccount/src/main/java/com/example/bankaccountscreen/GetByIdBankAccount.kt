package com.example.bankaccountscreen

import com.example.common.request
import com.example.data.repository.BankAccountRepository
import com.example.model.BankAccount
import jakarta.inject.Inject

class GetByIdBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(id: Int): Result<BankAccount> {
        return request { bankAccountRepository.getById(id) }
    }
}