package com.jonas.cookly.presentation.features.register.data.source

import com.jonas.cookly.core.data.remote.response.toSimplesResponseModel
import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.map
import com.jonas.cookly.presentation.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.presentation.features.register.domain.model.toAddUserRequest
import com.jonas.cookly.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import javax.inject.Inject

class RegisterUserRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipesServiceApi
) : RegisterUserRemoteDataSource {

    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel> {
        return recipesServiceApi
            .register(addUserRequestModel.toAddUserRequest())
            .map { it.toSimplesResponseModel() }
    }
}