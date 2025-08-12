package com.jonas.cookly.ui.features.login.domain.usecase

import com.jonas.cookly.core.domain.UserData
import com.jonas.cookly.core.util.FlowTask
import com.jonas.cookly.ui.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take
import javax.inject.Inject

interface GetUserDataUseCase {
    suspend operator fun invoke(params: Unit = Unit): Flow<UserData>
}

class GetUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : GetUserDataUseCase, FlowTask<Unit, UserData>() {

    override suspend fun execute(params: Unit): Flow<UserData> {
        return loginRepository.getData()
            .catch { emit(UserData(errorMessage = it.message ?: "Erro desconhecido")) }
            .take(1)
    }
}