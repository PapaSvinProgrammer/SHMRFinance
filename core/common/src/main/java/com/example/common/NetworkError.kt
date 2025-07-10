package com.example.common

sealed class NetworkThrowable: Throwable()

class SuccessDeleteTransactionException: NetworkThrowable()
class SerializationExceptionCustom: NetworkThrowable()
class RequestTimeoutException: NetworkThrowable()
class HaveTransactionException: NetworkThrowable()
class ServerErrorException: NetworkThrowable()
class UnknownException: NetworkThrowable()
class NoInternetException: NetworkThrowable()

fun NetworkThrowable.toSlug(): String {
    return when (this) {
        is HaveTransactionException -> "Нужно удалить все транзации счета для его удаления"
        is NoInternetException -> "Отсутсвует интернет"
        is RequestTimeoutException -> "Превышено время ожидания"
        is SerializationExceptionCustom -> "Ошибка в представлении объекта"
        is ServerErrorException -> "Ошибка на стороне сервера"
        is SuccessDeleteTransactionException -> "Успешное удаление"
        is UnknownException -> "Неизвестная ошибка"
    }
}