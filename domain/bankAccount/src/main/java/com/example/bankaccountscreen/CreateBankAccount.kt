package com.example.bankaccountscreen

import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.model.AccountRequest
import com.example.model.BankAccount
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CreateBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
    private val bankAccountRepositoryRoom: BankAccountRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<AccountRequest, Result<BankAccount>>(Dispatchers.IO) {
    override suspend fun run(params: AccountRequest): Result<BankAccount> {
        if (networkConnection.isOnline()) {
            return bankAccountRepository.create(params)
        }

        return bankAccountRepository.create(params)
    }
}