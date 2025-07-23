package com.example.security.internal

import android.content.Context
import com.example.security.external.KeyStoreRepository
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

internal class KeyStoreRepositoryImpl @Inject constructor(
    private val context: Context,
    private val keyStoreManager: KeyStoreManager
): KeyStoreRepository {
    override fun encrypt(value: String): Result<String> {
        return try {
            val bytes = value.encodeToByteArray()
            val file = File(context.filesDir, FILE_NAME)

            if (!file.exists()) {
                file.createNewFile()
            }

            val fos = FileOutputStream(file)

            val encryptedMessage = keyStoreManager.encrypt(
                bytes = bytes,
                outputStream = fos
            )

            Result.success(encryptedMessage.decodeToString())
        }
        catch (e: Exception) {
            Result.failure(SecurityException(e.message))
        }
    }

    override fun decrypt(value: String): Result<String> {
        return try {
            val file = File(context.filesDir, FILE_NAME)

            val decrypted = keyStoreManager.decrypt(
                inputStream = FileInputStream(file)
            )

            Result.success(decrypted.decodeToString())
        }
        catch (e: Exception) {
            Result.failure(SecurityException(e.message))
        }
    }

    private companion object {
        const val FILE_NAME = "secret.txt"
    }
}
