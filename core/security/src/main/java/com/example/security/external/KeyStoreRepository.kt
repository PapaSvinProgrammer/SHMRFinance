package com.example.security.external

interface KeyStoreRepository {
    fun encrypt(value: String): Result<String>
    fun decrypt(value: String): Result<String>
}