package com.jonas.cookly.core.data.remote.service

import com.jonas.cookly.core.data.remote.requests.auth.AddUserRequest
import com.jonas.cookly.core.data.remote.requests.auth.AuthUserRequest
import com.jonas.cookly.core.data.remote.requests.recipes.AddUpdateRecipeRequest
import com.jonas.cookly.core.data.remote.response.SimplesResponse
import com.jonas.cookly.core.data.remote.response.auth.TokenResponse
import com.jonas.cookly.core.data.remote.response.auth.UserResponse
import com.jonas.cookly.core.data.remote.response.recipes.RecipeDetailsResponse
import com.jonas.cookly.core.data.remote.response.recipes.RecipesResponse
import com.jonas.cookly.core.data.remote.util.safeApiCall
import com.jonas.cookly.core.util.ServiceResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
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

    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponse>> {
        return safeApiCall {
            client.get("recipes") {
                category?.let { parameter("category", it) }
            }.body()
        }
    }

    override suspend fun searchRecipes(nameOrIngredient: String): ServiceResult<List<RecipesResponse>> {
        return safeApiCall {
            client.get("recipes/search") {
                parameter("nameOrIngredient", nameOrIngredient)
            }.body()
        }
    }

    override suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailsResponse> {
        return safeApiCall {
            client.get("recipes/$recipeId").body()
        }
    }

    override suspend fun addRecipe(
        addRecipeRequest: AddUpdateRecipeRequest
    ): ServiceResult<SimplesResponse> {
        return safeApiCall {
            client.post("recipes") {
                setBody(addRecipeRequest)
            }.body()
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addRecipeRequest: AddUpdateRecipeRequest
    ): ServiceResult<SimplesResponse> {
        return safeApiCall {
            client.put("recipes/$recipeId") {
                setBody(addRecipeRequest)
            }.body()
        }
    }

    override suspend fun deleteRecipe(recipeId: String): ServiceResult<SimplesResponse> {
        return safeApiCall {
            client.delete("recipes/$recipeId").body()
        }
    }
}
