package com.jonas.cookly.core.di

import com.google.gson.Gson
import com.jonas.cookly.BuildConfig.BASE_URL
import com.jonas.cookly.core.data.remote.service.RecipesServiceApi
import com.jonas.cookly.core.data.remote.service.RecipesServiceApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import okhttp3.OkHttpClient
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun providesHttpClient(
        okHttpClient: OkHttpClient
    ): HttpClient {
        return HttpClient(OkHttp) {
            defaultRequest {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }
            engine {
                preconfigured = okHttpClient
                config { }
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.tag("Logger Ktor -> ").v(message)
                    }
                }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                gson {
                    setLenient()
                    setPrettyPrinting()
                    serializeNulls()
                }
            }
            install(WebSockets)
        }
    }

    @Provides
    @Singleton
    fun provideRecipesServiceApi(httpClient: HttpClient): RecipesServiceApi {
        return RecipesServiceApiImpl(httpClient)
    }
}
