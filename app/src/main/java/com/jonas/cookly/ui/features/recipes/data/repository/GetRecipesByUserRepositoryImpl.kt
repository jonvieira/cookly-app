package com.jonas.cookly.ui.features.recipes.data.repository

import com.jonas.cookly.core.domain.RecipesResponseModel
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.features.recipes.domain.repository.GetRecipesByUserRepository
import com.jonas.cookly.ui.features.recipes.domain.source.GetRecipesByUserRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecipesByUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetRecipesByUserRemoteDataSource,
    private val dispatcher: DispatchersProvider
) : GetRecipesByUserRepository {

    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return withContext(dispatcher.io()) {
            remoteDataSource.getRecipesByUser(category)
        }
    }
}