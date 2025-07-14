package com.example.bankaccountscreen

import com.example.data.external.local.BankAccountRepositoryRoom
import com.example.data.external.remote.BankAccountRepository
import com.example.model.BankAccount
import com.example.network.connectivityState.NetworkConnection
import com.example.utils.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAllBankAccount @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
    private val bankAccountRepositoryRoom: BankAccountRepositoryRoom,
    private val networkConnection: NetworkConnection
) : UseCase<Unit, Result<List<BankAccount>>>(Dispatchers.IO) {
    override suspend fun run(params: Unit): Result<List<BankAccount>> {
        if (networkConnection.isOnline()) {
            return bankAccountRepository.getAll()
        }

        return bankAccountRepositoryRoom.getAll()
    }
}
