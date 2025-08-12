package com.jonas.cookly.ui.features.recipes.domain.usecase

import com.jonas.cookly.core.domain.RecipesResponseModel
import com.jonas.cookly.core.util.ResponseData
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.core.util.Task
import com.jonas.cookly.ui.features.recipes.domain.repository.GetRecipesByUserRepository
import com.jonas.cookly.ui.features.recipes.domain.usecase.GetRecipesByUserUseCase.Params
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRecipesByUserUseCase {
    operator fun invoke(params: Params): Flow<ResponseData<List<RecipesResponseModel>>>
    data class Params(val category: Int? = null)
}

class GetRecipesByUserUseCaseImpl @Inject constructor(
    private val getRecipesByUserRepository: GetRecipesByUserRepository
) : GetRecipesByUserUseCase, Task<Params, List<RecipesResponseModel>>() {

    override suspend fun execute(params: Params): ResponseData<List<RecipesResponseModel>> {
        return try {
            when (val response = getRecipesByUserRepository.getRecipesByUser(params.category)) {
                is ServiceResult.Success -> {
                    if(response.data.isEmpty()) {
                        ResponseData.Empty
                    } else {
                        ResponseData.Success(response.data)
                    }
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}