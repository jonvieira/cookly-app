package com.jonas.cookly.ui.features.register.di

import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.ui.features.register.data.repository.RegisterUserRepositoryImpl
import com.jonas.cookly.ui.features.register.data.source.RegisterUserRemoteDataSourceImpl
import com.jonas.cookly.ui.features.register.domain.repository.RegisterUserRepository
import com.jonas.cookly.ui.features.register.domain.source.RegisterUserRemoteDataSource
import com.jonas.cookly.ui.features.register.domain.usecase.RegisterUserUseCase
import com.jonas.cookly.ui.features.register.domain.usecase.RegisterUserUseCaseImpl
import com.jonas.cookly.ui.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.jonas.cookly.ui.features.register.domain.usecase.ValidateRegisterInputUseCaseImpl
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
        registerUserRemoteDataSource: RegisterUserRemoteDataSource,
        dispatchers: DispatchersProvider
    ): RegisterUserRepository =
        RegisterUserRepositoryImpl(
            remoteDataSource = registerUserRemoteDataSource,
            dispatchers = dispatchers
        )

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(
        registerUserRepository: RegisterUserRepository
    ): RegisterUserUseCase = RegisterUserUseCaseImpl(
        registerUserRepository = registerUserRepository
    )

    @Provides
    @Singleton
    fun provideValidateRegisterUserUseCase(): ValidateRegisterInputUseCase =
        ValidateRegisterInputUseCaseImpl()
}
