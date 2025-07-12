package com.jonas.cookly.core.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

abstract class Task<in Params, Result> {

    operator fun invoke(params: Params): Flow<ResponseData<Result>> = flow {
        emit(ResponseData.Loading)
        emit(execute(params))
    }.catch { throwable ->
        emit(ResponseData.Error(throwable))
    }

    protected abstract suspend fun execute(params: Params): ResponseData<Result>
}

abstract class FlowTask<in Params, Result : Any> {

    suspend operator fun invoke(params: Params): Flow<Result> = execute(params)

    protected abstract suspend fun execute(params: Params): Flow<Result>
}
