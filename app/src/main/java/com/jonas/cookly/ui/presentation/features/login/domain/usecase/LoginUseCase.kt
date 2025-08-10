package com.jonas.cookly.ui.presentation.features.login.domain.usecase

import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.ResponseData
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.Task
import com.jonas.cookly.ui.presentation.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.presentation.features.login.domain.model.TokenResponseModel
import com.jonas.cookly.ui.presentation.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface LoginUseCase {
    operator fun invoke(params: Params): Flow<ResponseData<TokenResponseModel>>
    data class Params(val authUserRequestModel: AuthUserRequestModel)
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatchers: DispatchersProvider
) : LoginUseCase, Task<LoginUseCase.Params, TokenResponseModel>() {

    override suspend fun execute(params: LoginUseCase.Params): ResponseData<TokenResponseModel> {
        return try {
            withContext(dispatchers.io()) {
                val response = loginRepository.login(params.authUserRequestModel)

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