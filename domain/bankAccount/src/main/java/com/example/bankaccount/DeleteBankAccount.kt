package com.example.bankaccount

import com.example.common.request
import com.example.data.repository.BankAccountRepository
import jakarta.inject.Inject

class DeleteBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(id: Int): Result<Unit> {
        return request { bankAccountRepository.delete(id) }
    }
}