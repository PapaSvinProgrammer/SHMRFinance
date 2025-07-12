package com.example.bankaccountscreen

import com.example.bankaccountscreen.model.UpdateBankParams
import com.example.data.external.BankAccountRepository
import com.example.model.BankAccount
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpdateBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository
): UseCase<UpdateBankParams, Result<BankAccount>>(Dispatchers.IO) {
    override suspend fun run(params: UpdateBankParams): Result<BankAccount> {
        return bankAccountRepository.update(
            id = params.id,
            request = params.request
        )
    }
}
