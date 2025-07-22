package com.example.security.external

interface KeyStoreRepository {
    fun encrypt(value: String): String
    fun decrypt(value: String): String
}