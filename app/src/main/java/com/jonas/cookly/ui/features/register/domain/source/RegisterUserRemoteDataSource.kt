package com.jonas.cookly.ui.features.register.domain.source

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.features.register.domain.model.AddUserRequestModel

interface RegisterUserRemoteDataSource {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel>
}
