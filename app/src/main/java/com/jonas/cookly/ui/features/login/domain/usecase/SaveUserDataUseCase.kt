package com.jonas.cookly.ui.features.login.domain.usecase

import com.jonas.cookly.core.domain.UserData
import com.jonas.cookly.core.util.ResponseData
import com.jonas.cookly.core.util.Task
import com.jonas.cookly.ui.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SaveUserDataUseCase {
    operator fun invoke(params: Params): Flow<ResponseData<Unit>>
    data class Params(val userData: UserData)
}

class SaveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : SaveUserDataUseCase, Task<SaveUserDataUseCase.Params, Unit>() {

    override suspend fun execute(params: SaveUserDataUseCase.Params): ResponseData<Unit> {
        return try {
            ResponseData.Success(loginRepository.saveData(userData = params.userData))
        } catch (e: Throwable) {
            ResponseData.Error(e)
        }
    }
}
