package com.jonas.cookly.ui.features.register.data.repository

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.ui.features.register.domain.repository.RegisterUserRepository
import com.jonas.cookly.ui.features.register.domain.source.RegisterUserRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource,
    private val dispatchers: DispatchersProvider
) : RegisterUserRepository {

    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel> {
        return withContext(dispatchers.io()) {
            remoteDataSource.registerUser(addUserRequestModel)
        }
    }
}
