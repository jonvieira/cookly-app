package com.jonas.cookly.ui.features.login.data.source

import com.jonas.cookly.core.data.remote.response.toTokenResponseModel
import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.map
import com.jonas.cookly.ui.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.features.login.domain.model.TokenResponseModel
import com.jonas.cookly.ui.features.login.domain.model.toAddUserRequest
import com.jonas.cookly.ui.features.login.domain.source.LoginRemoteDataSource
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipesServiceApi
) : LoginRemoteDataSource {

    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return recipesServiceApi
            .login(authUserRequestModel.toAddUserRequest())
            .map { it.toTokenResponseModel() }
    }
}