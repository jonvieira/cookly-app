package com.jonas.cookly.core.data.remote.service

import com.jonas.cookly.core.data.remote.requests.auth.AddUserRequest
import com.jonas.cookly.core.data.remote.requests.auth.AuthUserRequest
import com.jonas.cookly.core.data.remote.requests.recipes.AddUpdateRecipeRequest
import com.jonas.cookly.core.data.remote.response.SimplesResponse
import com.jonas.cookly.core.data.remote.response.auth.TokenResponse
import com.jonas.cookly.core.data.remote.response.auth.UserResponse
import com.jonas.cookly.core.data.remote.response.recipes.RecipeDetailsResponse
import com.jonas.cookly.core.data.remote.response.recipes.RecipesResponse
import com.jonas.cookly.core.util.ServiceResult

interface RecipesServiceApi {
    suspend fun login(authUserRequest: AuthUserRequest): ServiceResult<TokenResponse>
    suspend fun register(addUserRequest: AddUserRequest): ServiceResult<SimplesResponse>
    suspend fun getProfileUser(): ServiceResult<UserResponse>

    suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponse>>
    suspend fun searchRecipes(nameOrIngredient: String): ServiceResult<List<RecipesResponse>>
    suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailsResponse>
    suspend fun addRecipe(addRecipeRequest: AddUpdateRecipeRequest): ServiceResult<SimplesResponse>
    suspend fun updateRecipe(recipeId: String, addRecipeRequest: AddUpdateRecipeRequest): ServiceResult<SimplesResponse>
    suspend fun deleteRecipe(recipeId: String): ServiceResult<SimplesResponse>
}
