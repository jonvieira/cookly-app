package com.jonas.cookly.core.data.local

import com.jonas.cookly.core.domain.UserData
import kotlinx.coroutines.flow.Flow

interface DataStoreLocalSource {
    fun getData(): Flow<UserData>
    suspend fun saveData(userData: UserData)
    suspend fun clearData()
}
