package com.jonas.cookly.core.data.remote.service

import com.jonas.cookly.core.data.remote.requests.AddUserRequest
import com.jonas.cookly.core.data.remote.requests.AuthUserRequest
import com.jonas.cookly.core.data.remote.response.SimplesResponse
import com.jonas.cookly.core.data.remote.response.TokenResponse
import com.jonas.cookly.core.data.remote.response.UserResponse
import com.jonas.cookly.core.util.ServiceResult

interface RecipesServiceApi {
    suspend fun login(authUserRequest: AuthUserRequest): ServiceResult<TokenResponse>
    suspend fun register(addUserRequest: AddUserRequest): ServiceResult<SimplesResponse>
    suspend fun getProfileUser(): ServiceResult<UserResponse>
}
