package com.example.bankaccountscreen

import com.example.common.request
import com.example.data.external.BankAccountRepository
import com.example.model.BankAccount
import javax.inject.Inject

class GetAllBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(): Result<List<BankAccount>> {
        return request { bankAccountRepository.getAll() }
    }
}