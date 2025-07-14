package com.example.bankaccountscreen

import com.example.bankaccountscreen.model.UpdateBankParams
import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.model.BankAccount
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpdateBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
    private val bankAccountRepositoryRoom: BankAccountRepositoryRoom,
    private val networkConnection: NetworkConnection
): UseCase<UpdateBankParams, Result<BankAccount>>(Dispatchers.IO) {
    override suspend fun run(params: UpdateBankParams): Result<BankAccount> {
        return bankAccountRepository.update(
            id = params.id,
            request = params.request
        )
    }
}
