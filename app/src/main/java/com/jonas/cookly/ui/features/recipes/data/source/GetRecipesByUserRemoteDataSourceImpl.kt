package com.jonas.cookly.ui.features.recipes.data.source

import com.jonas.cookly.core.data.remote.response.recipes.toRecipesResponseModel
import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.domain.RecipesResponseModel
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.mapper
import com.jonas.cookly.ui.features.recipes.domain.source.GetRecipesByUserRemoteDataSource
import javax.inject.Inject

class GetRecipesByUserRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipesServiceApi
) : GetRecipesByUserRemoteDataSource {

    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return recipesServiceApi
            .getRecipesByUser(category)
            .mapper { response ->
                response.map { it.toRecipesResponseModel() }
            }
    }
}
