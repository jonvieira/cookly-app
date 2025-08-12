package com.jonas.cookly.ui.features.login.di

import com.jonas.cookly.core.data.local.DataStoreLocalSource
import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.ui.features.login.data.repository.LoginRepositoryImpl
import com.jonas.cookly.ui.features.login.data.source.LoginRemoteDataSourceImpl
import com.jonas.cookly.ui.features.login.domain.repository.LoginRepository
import com.jonas.cookly.ui.features.login.domain.source.LoginRemoteDataSource
import com.jonas.cookly.ui.features.login.domain.usecase.GetUserDataUseCase
import com.jonas.cookly.ui.features.login.domain.usecase.GetUserDataUseCaseImpl
import com.jonas.cookly.ui.features.login.domain.usecase.LoginUseCase
import com.jonas.cookly.ui.features.login.domain.usecase.LoginUseCaseImpl
import com.jonas.cookly.ui.features.login.domain.usecase.RemoveUserDataUseCase
import com.jonas.cookly.ui.features.login.domain.usecase.RemoveUserDataUseCaseImpl
import com.jonas.cookly.ui.features.login.domain.usecase.SaveUserDataUseCase
import com.jonas.cookly.ui.features.login.domain.usecase.SaveUserDataUseCaseImpl
import com.jonas.cookly.ui.features.login.domain.usecase.ValidateLoginInputUseCase
import com.jonas.cookly.ui.features.login.domain.usecase.ValidateLoginInputUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource,
        localDataSource: DataStoreLocalSource,
        dispatchers: DispatchersProvider
    ): LoginRepository {
        return LoginRepositoryImpl(
            remoteDataSource = loginRemoteDataSource,
            localDataSource = localDataSource,
            dispatchers = dispatchers
        )
    }

    @Provides
    fun provideLoginRemoteDataSource(recipesServiceApi: RecipesServiceApi): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(recipesServiceApi = recipesServiceApi)
    }

    @Provides
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }

    @Provides
    fun provideGetUserDataUseCase(repository: LoginRepository): GetUserDataUseCase {
        return GetUserDataUseCaseImpl(loginRepository = repository)
    }

    @Provides
    fun provideSaveUserDataUseCase(repository: LoginRepository): SaveUserDataUseCase {
        return SaveUserDataUseCaseImpl(loginRepository = repository)
    }

    @Provides
    fun providesRemoveUserDataUseCase(repository: LoginRepository): RemoveUserDataUseCase {
        return RemoveUserDataUseCaseImpl(loginRepository = repository)
    }
}