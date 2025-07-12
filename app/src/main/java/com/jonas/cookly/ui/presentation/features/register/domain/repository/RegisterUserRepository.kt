package com.jonas.cookly.ui.presentation.features.register.domain.repository

import com.jonas.cookly.core.domain.SimplesResponseModel
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.presentation.features.register.domain.model.AddUserRequestModel

interface RegisterUserRepository {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel>
}
