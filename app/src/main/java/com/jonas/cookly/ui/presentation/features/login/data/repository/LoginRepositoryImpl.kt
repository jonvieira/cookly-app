package com.jonas.cookly.ui.presentation.features.login.data.repository

import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.presentation.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.presentation.features.login.domain.model.TokenResponseModel
import com.jonas.cookly.ui.presentation.features.login.domain.repository.LoginRepository
import com.jonas.cookly.ui.presentation.features.login.domain.source.LoginRemoteDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return loginRemoteDataSource.login(authUserRequestModel)
    }
}