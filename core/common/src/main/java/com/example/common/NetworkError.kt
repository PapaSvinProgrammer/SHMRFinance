package com.example.common

enum class NetworkError: RootError {
    NO_INTERNET,
    REQUEST_TIMEOUT,
    SUCCESS_DELETE_TRANSACTION,
    SERVER_ERROR,
    SERIALIZATION,
    HAVE_TRANSACTION,
    UNKNOWN
}

fun NetworkError.toSlug(): String {
    return when (this) {
        NetworkError.NO_INTERNET -> "Отсутсвует интернет"
        NetworkError.REQUEST_TIMEOUT -> "Превышено время ожидания"
        NetworkError.SUCCESS_DELETE_TRANSACTION -> "Успешное удаление"
        NetworkError.SERVER_ERROR -> "Ошибка на стороне сервера"
        NetworkError.SERIALIZATION -> "Ошибка в представлении объекта"
        NetworkError.UNKNOWN -> "Неизвестная ошибка"
        NetworkError.HAVE_TRANSACTION -> "Нужно удалить все транзации счета для его удаления"
    }
}