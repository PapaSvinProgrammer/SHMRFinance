package com.example.bankaccountscreen

import com.example.data.external.remote.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CreateBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) : UseCase<AccountRequest, Result<BankAccount>>(Dispatchers.IO) {
    override suspend fun run(params: AccountRequest): Result<BankAccount> {
        return bankAccountRepository.create(params)
    }
}