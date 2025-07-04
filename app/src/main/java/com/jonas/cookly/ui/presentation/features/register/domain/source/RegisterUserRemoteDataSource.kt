package com.jonas.cookly.ui.presentation.features.register.domain.source

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.ui.presentation.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.core.util.ServiceResult

interface RegisterUserRemoteDataSource {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel>
}