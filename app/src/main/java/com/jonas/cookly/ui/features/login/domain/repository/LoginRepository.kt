package com.jonas.cookly.ui.features.login.domain.repository

import com.jonas.cookly.core.domain.UserData
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.features.login.domain.model.TokenResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel>
    fun getData(): Flow<UserData>
    suspend fun saveData(userData: UserData)
    suspend fun clearData()
}
