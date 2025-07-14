package com.example.bankaccountscreen

import com.example.data.external.remote.BankAccountRepository
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) : UseCase<Int, Result<Unit>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Unit> {
        return bankAccountRepository.delete(params)
    }
}