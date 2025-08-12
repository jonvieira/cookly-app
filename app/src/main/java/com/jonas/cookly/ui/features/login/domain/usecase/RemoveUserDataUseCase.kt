package com.jonas.cookly.ui.features.login.domain.usecase

import com.jonas.cookly.core.util.FlowTask
import com.jonas.cookly.core.util.ResponseData
import com.jonas.cookly.ui.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoveUserDataUseCase {
    suspend operator fun invoke(params: Unit): Flow<ResponseData<Unit>>
}

class RemoveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : RemoveUserDataUseCase, FlowTask<Unit, ResponseData<Unit>>() {

    override suspend fun execute(params: Unit): Flow<ResponseData<Unit>> {
        return flow {
            try {
                emit(ResponseData.Success(loginRepository.clearData()))
            } catch (e: Throwable) {
                emit(ResponseData.Error(e))
            }
        }
    }
}