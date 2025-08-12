package com.jonas.cookly.ui.features.recipes.domain.source

import com.jonas.cookly.core.domain.RecipesResponseModel
import com.jonas.cookly.core.util.ServiceResult

interface GetRecipesByUserRemoteDataSource {
    suspend fun getRecipesByUser(category: Int? = null): ServiceResult<List<RecipesResponseModel>>
}
