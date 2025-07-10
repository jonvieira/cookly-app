package com.jonas.cookly.core.util

sealed class ServiceResult<out T> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val code: String? = null, val message: String) : ServiceResult<Nothing>()
}

fun <T, R> ServiceResult<T>.map(transform: (T) -> R): ServiceResult<R> {
    return when (this) {
        is ServiceResult.Success -> ServiceResult.Success(transform(data))
        is ServiceResult.Error -> ServiceResult.Error(code, message)
    }
}