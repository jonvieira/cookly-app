package com.jonas.cookly.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jonas.cookly.core.data.local.DataStoreLocalSource
import com.jonas.cookly.core.data.local.DataStoreLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreData(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile("cookly_preferences")
            }
        )

    @Provides
    @Singleton
    fun provideDataStoreLocalSource(dataStore: DataStore<Preferences>): DataStoreLocalSource =
        DataStoreLocalSourceImpl(dataStore)
}