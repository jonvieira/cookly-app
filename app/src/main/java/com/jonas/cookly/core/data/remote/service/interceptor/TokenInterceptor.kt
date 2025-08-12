package com.jonas.cookly.core.data.remote.service.interceptor

import com.jonas.cookly.core.data.local.DataStoreLocalSource
import com.jonas.cookly.core.util.DispatchersProvider
import com.jonas.cookly.core.util.logging.logInfo
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val localDataStore: DataStoreLocalSource,
    private val dispatchers: DispatchersProvider
) : Interceptor {

    companion object {
        const val TOKEN_TYPE = "Bearer"
        const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val data = try {
            runBlocking(dispatchers.io()) {
                localDataStore.getData().firstOrNull()
            }
        } catch (e: Exception) {
            logInfo("TOKEN_INTERCEPTOR", "Error getting data: $e")
            null
        }

        val request = chain.request().newBuilder()

        data?.token?.let { token ->
            request.addHeader(AUTHORIZATION, "$TOKEN_TYPE $token")
        } ?: run {
            logInfo("TOKEN_INTERCEPTOR", "Token not found")
        }

        return chain.proceed(request.build())
    }
}