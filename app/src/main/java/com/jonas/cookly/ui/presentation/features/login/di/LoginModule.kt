package com.jonas.cookly.ui.presentation.features.login.di

import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.ui.presentation.features.login.data.repository.LoginRepositoryImpl
import com.jonas.cookly.ui.presentation.features.login.data.source.LoginRemoteDataSourceImpl
import com.jonas.cookly.ui.presentation.features.login.domain.repository.LoginRepository
import com.jonas.cookly.ui.presentation.features.login.domain.source.LoginRemoteDataSource
import com.jonas.cookly.ui.presentation.features.login.domain.usecase.LoginUseCase
import com.jonas.cookly.ui.presentation.features.login.domain.usecase.LoginUseCaseImpl
import com.jonas.cookly.ui.presentation.features.login.domain.usecase.ValidateLoginInputUseCase
import com.jonas.cookly.ui.presentation.features.login.domain.usecase.ValidateLoginInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        dispatchers: DispatchersProvider
    ): LoginUseCase {
        return LoginUseCaseImpl(loginRepository = loginRepository, dispatchers = dispatchers)
    }

    @Provides
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(loginRemoteDataSource = loginRemoteDataSource)
    }

    @Provides
    fun provideLoginRemoteDataSource(
        recipesServiceApi: RecipesServiceApi
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)
    }

    @Provides
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }
}