package com.example.security.external

interface KeyStoreRepository {
    fun encrypt(value: String): Result<String>
    fun decrypt(): Result<String>
    fun delete(): Result<Unit>
}
