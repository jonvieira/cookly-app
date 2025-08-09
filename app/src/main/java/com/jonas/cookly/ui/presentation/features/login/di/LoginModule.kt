package com.jonas.cookly.ui.presentation.features.login.di

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
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCaseImpl()
    }
}