package com.example.bankaccountscreen

import com.example.common.request
import com.example.data.external.BankAccountRepository
import com.example.model.BankAccountHistoryResponse
import javax.inject.Inject

class GetUpdateHistoryBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) {
    suspend fun execute(id: Int): Result<BankAccountHistoryResponse> {
        return request { bankAccountRepository.getUpdateHistory(id) }
    }
}