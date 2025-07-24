package com.example.security.internal.di

import com.example.security.external.KeyStoreRepository

interface SecurityDependency {
    val keyStoreRepository: KeyStoreRepository
}