package com.example.bankaccountscreen

import com.example.data.external.BankAccountRepository
import com.example.model.BankAccountHistoryResponse
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetUpdateHistoryBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) : UseCase<Int, Result<BankAccountHistoryResponse>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<BankAccountHistoryResponse> {
        return bankAccountRepository.getUpdateHistory(params)
    }
}
