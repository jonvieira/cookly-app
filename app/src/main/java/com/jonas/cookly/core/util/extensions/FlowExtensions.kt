package com.jonas.cookly.core.util.extensions

import com.jonas.cookly.core.util.ResponseData
import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<ResponseData<T>>.observeState(
    onEmpty: suspend () -> Unit = {},
    onLoading: suspend () -> Unit,
    onError: suspend (error: Throwable) -> Unit,
    onSuccess: suspend (data: T) -> Unit
) {
    collect { response ->
        when (response) {
            is ResponseData.Loading -> onLoading()
            is ResponseData.Empty -> onEmpty()
            is ResponseData.Error -> onError(response.throwable)
            is ResponseData.Success -> onSuccess(response.data)
        }
    }
}