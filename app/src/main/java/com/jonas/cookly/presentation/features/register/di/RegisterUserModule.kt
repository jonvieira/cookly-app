package com.jonas.cookly.presentation.features.register.di

import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.presentation.features.register.data.repository.RegisterUserRepositoryImpl
import com.jonas.cookly.presentation.features.register.data.source.RegisterUserRemoteDataSourceImpl
import com.jonas.cookly.presentation.features.register.domain.repository.RegisterUserRepository
import com.jonas.cookly.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import com.jonas.cookly.presentation.features.register.domain.usecase.RegisterUserUseCase
import com.jonas.cookly.presentation.features.register.domain.usecase.RegisterUserUseCaseImpl
import com.jonas.cookly.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.jonas.cookly.presentation.features.register.domain.usecase.ValidateRegisterInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterUserModule {

    @Provides
    @Singleton
    fun provideRegisterUserDataSource(
        recipesServiceApi: RecipesServiceApi
    ): RegisterUserRemoteDataSource =
        RegisterUserRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)

    @Provides
    @Singleton
    fun provideRegisterUserRepository(
        registerUserRemoteDataSource: RegisterUserRemoteDataSource
    ): RegisterUserRepository =
        RegisterUserRepositoryImpl(remoteDataSource = registerUserRemoteDataSource)

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(
        registerUserRepository: RegisterUserRepository,
        dispatchersProvider: DispatchersProvider
    ): RegisterUserUseCase = RegisterUserUseCaseImpl(
        registerUserRepository = registerUserRepository,
        dispatchers = dispatchersProvider
    )

    @Provides
    @Singleton
    fun provideValidateRegisterUserUseCase(): ValidateRegisterInputUseCase =
        ValidateRegisterInputUseCaseImpl()
}
