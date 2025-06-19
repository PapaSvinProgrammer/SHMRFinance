package com.example.common


sealed interface Result<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D):
        Result<D, E>
    data class Error<out D, out E: RootError>(val error: E):
        Result<D, E>

    fun onSuccess(block: (D) -> Unit): Result<D, E> {
        if (this is Success) block(data)
        return this
    }

    fun onError(block: (E) -> Unit): Result<D, E> {
        if (this is Error) block(error)
        return this
    }
}

inline fun <T, E: RootError, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}