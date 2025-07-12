package com.jonas.cookly.core.data.remote.service

import com.jonas.cookly.core.data.remote.requests.AddUserRequest
import com.jonas.cookly.core.data.remote.requests.AuthUserRequest
import com.jonas.cookly.core.data.remote.response.SimplesResponse
import com.jonas.cookly.core.data.remote.response.TokenResponse
import com.jonas.cookly.core.data.remote.response.UserResponse
import com.jonas.cookly.core.data.remote.util.safeApiCall
import com.jonas.cookly.core.util.ServiceResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class RecipesServiceApiImpl @Inject constructor(
    private val client: HttpClient
) : RecipesServiceApi {

    override suspend fun login(authUserRequest: AuthUserRequest): ServiceResult<TokenResponse> {
        return safeApiCall {
            client.post("users/login") {
                setBody(authUserRequest)
            }.body()
        }
    }

    override suspend fun register(addUserRequest: AddUserRequest): ServiceResult<SimplesResponse> {
        return safeApiCall {
            client.post("users/register") {
                setBody(addUserRequest)
            }.body()
        }
    }

    override suspend fun getProfileUser(): ServiceResult<UserResponse> {
        return safeApiCall { client.get("users/profile").body() }
    }
}
