package com.example.bankaccountscreen

import com.example.common.request
import com.example.data.external.BankAccountRepository
import javax.inject.Inject

class DeleteBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(id: Int): Result<Unit> {
        return request { bankAccountRepository.delete(id) }
    }
}