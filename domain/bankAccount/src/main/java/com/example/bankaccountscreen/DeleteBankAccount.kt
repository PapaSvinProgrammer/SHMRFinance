package com.example.bankaccountscreen

import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
    private val bankAccountRepositoryRoom: BankAccountRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<Int, Result<Unit>>(Dispatchers.IO) {
    override suspend fun run(params: Int): Result<Unit> {
        if (networkConnection.isOnline()) {
            return bankAccountRepository.delete(params)
        }

        return bankAccountRepositoryRoom.delete(params)
    }
}