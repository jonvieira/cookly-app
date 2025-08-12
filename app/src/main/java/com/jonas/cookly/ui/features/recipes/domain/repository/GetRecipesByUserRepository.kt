package com.jonas.cookly.ui.features.recipes.domain.repository

import com.jonas.cookly.core.domain.RecipesResponseModel
import com.jonas.cookly.core.util.ServiceResult

interface GetRecipesByUserRepository {
    suspend fun getRecipesByUser(category: Int? = null): ServiceResult<List<RecipesResponseModel>>
}
