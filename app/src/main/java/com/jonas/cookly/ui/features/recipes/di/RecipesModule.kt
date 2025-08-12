package com.jonas.cookly.ui.features.recipes.di

import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.ui.features.recipes.data.repository.GetRecipesByUserRepositoryImpl
import com.jonas.cookly.ui.features.recipes.data.source.GetRecipesByUserRemoteDataSourceImpl
import com.jonas.cookly.ui.features.recipes.domain.repository.GetRecipesByUserRepository
import com.jonas.cookly.ui.features.recipes.domain.source.GetRecipesByUserRemoteDataSource
import com.jonas.cookly.ui.features.recipes.domain.usecase.GetRecipesByUserUseCase
import com.jonas.cookly.ui.features.recipes.domain.usecase.GetRecipesByUserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipesModule {

    @Provides
    @Singleton
    fun provideGetRecipesByUserRemoteDataSource(
        recipesServiceApi: RecipesServiceApi
    ): GetRecipesByUserRemoteDataSource {
        return GetRecipesByUserRemoteDataSourceImpl(
            recipesServiceApi = recipesServiceApi
        )
    }

    @Provides
    @Singleton
    fun provideGetRecipesByUserRepository(
        remoteDataSource: GetRecipesByUserRemoteDataSource,
        dispatcher: DispatchersProvider
    ): GetRecipesByUserRepository {
        return GetRecipesByUserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            dispatcher = dispatcher
        )
    }

    @Provides
    @Singleton
    fun provideGetRecipesByUserUseCase(
        getRecipesByUserRepository: GetRecipesByUserRepository
    ): GetRecipesByUserUseCase {
        return GetRecipesByUserUseCaseImpl(
            getRecipesByUserRepository = getRecipesByUserRepository
        )
    }
}
