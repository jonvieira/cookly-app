package com.jonas.cookly.ui.features.login.data.repository

import com.jonas.cookly.core.data.local.DataStoreLocalSource
import com.jonas.cookly.core.domain.UserData
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.ServiceResult
import com.jonas.cookly.ui.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.features.login.domain.model.TokenResponseModel
import com.jonas.cookly.ui.features.login.domain.repository.LoginRepository
import com.jonas.cookly.ui.features.login.domain.source.LoginRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: DataStoreLocalSource,
    private val dispatchers: DispatchersProvider
) : LoginRepository {

    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return withContext(dispatchers.io()) {
            remoteDataSource.login(authUserRequestModel)
        }
    }

    override suspend fun saveData(userData: UserData) {
        return withContext(dispatchers.io()) {
            localDataSource.saveData(userData)
        }
    }

    override fun getData(): Flow<UserData> = localDataSource.getData()

    override suspend fun clearData() {
        return withContext(dispatchers.io()) {
            localDataSource.clearData()
        }
    }
}
