package com.jonas.cookly.ui.presentation.features.register.domain.repository

import com.jonas.cookly.ui.presentation.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.domain.SimplesResponseModel

interface RegisterUserRepository {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel>
}