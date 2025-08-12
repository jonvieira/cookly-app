package com.jonas.cookly.ui.features.register.domain.usecase

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.core.util.ResponseData
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.Task
import com.jonas.cookly.ui.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.ui.features.register.domain.repository.RegisterUserRepository
import com.jonas.cookly.ui.features.register.domain.usecase.RegisterUserUseCase.Params
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RegisterUserUseCase {
    operator fun invoke(params: Params): Flow<ResponseData<SimplesResponseModel>>
    data class Params(val addUserRequestModel: AddUserRequestModel)
}

class RegisterUserUseCaseImpl @Inject constructor(
    private val registerUserRepository: RegisterUserRepository
) : RegisterUserUseCase, Task<Params, SimplesResponseModel>() {

    override suspend fun execute(params: Params): ResponseData<SimplesResponseModel> {
        return try {
            when (val response = registerUserRepository.registerUser(params.addUserRequestModel)) {
                is ServiceResult.Success -> {
                    ResponseData.Success(response.data)
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}
