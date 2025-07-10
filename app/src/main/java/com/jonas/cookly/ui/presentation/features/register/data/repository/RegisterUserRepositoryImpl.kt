package com.jonas.cookly.ui.presentation.features.register.data.repository

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.ui.presentation.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.presentation.features.register.domain.repository.RegisterUserRepository
import com.jonas.cookly.ui.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import javax.inject.Inject

class RegisterUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource
) : RegisterUserRepository {

    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel> {
        return remoteDataSource.registerUser(addUserRequestModel)
    }
}