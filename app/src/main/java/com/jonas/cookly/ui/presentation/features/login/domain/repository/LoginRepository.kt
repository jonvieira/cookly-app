package com.jonas.cookly.ui.presentation.features.login.domain.repository

import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.presentation.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.presentation.features.login.domain.model.TokenResponseModel

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel>
}