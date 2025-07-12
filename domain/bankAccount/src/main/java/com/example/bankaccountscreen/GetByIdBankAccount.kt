package com.example.bankaccountscreen

import com.example.data.external.BankAccountRepository
import com.example.model.BankAccount
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetByIdBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) : UseCase<Int, Result<BankAccount>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<BankAccount> {
        return bankAccountRepository.getById(params)
    }
}