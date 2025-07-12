package com.example.bankaccountscreen

import com.example.data.external.BankAccountRepository
import com.example.model.BankAccount
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAllBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
) : UseCase<Unit, Result<List<BankAccount>>>(Dispatchers.IO) {
    override suspend fun run(params: Unit): Result<List<BankAccount>> {
        return bankAccountRepository.getAll()
    }
}
