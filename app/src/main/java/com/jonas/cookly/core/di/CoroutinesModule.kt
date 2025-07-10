package com.jonas.cookly.core.di

import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.DispatchersProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Binds
    fun provideDispatchersProvider(dispatchersProvider: DispatchersProviderImpl): DispatchersProvider
}