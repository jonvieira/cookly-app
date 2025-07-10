package com.jonas.cookly.ui.presentation.features.register.domain.usecase

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.ResponseData
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.Task
import com.jonas.cookly.ui.presentation.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.ui.presentation.features.register.domain.repository.RegisterUserRepository
import com.jonas.cookly.ui.presentation.features.register.domain.usecase.RegisterUserUseCase.Params
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RegisterUserUseCase {
    operator fun invoke(params: Params): Flow<ResponseData<SimplesResponseModel>>
    data class Params(val addUserRequestModel: AddUserRequestModel)
}

class RegisterUserUseCaseImpl @Inject constructor(
    private val registerUserRepository: RegisterUserRepository,
    private val dispatchers: DispatchersProvider
) : RegisterUserUseCase, Task<Params, SimplesResponseModel>() {

    override suspend fun execute(params: Params): ResponseData<SimplesResponseModel> {
        return try {
            withContext(dispatchers.io()) {
                val response = registerUserRepository.registerUser(params.addUserRequestModel)

                when (response) {
                    is ServiceResult.Success -> {
                        ResponseData.Success(response.data)
                    }

                    is ServiceResult.Error -> {
                        ResponseData.Error(Throwable(response.message))
                    }
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}