package com.jonas.cookly.core.data.remote.util

import com.jonas.cookly.core.domain.ErrorResponseException
import com.jonas.cookly.core.util.ServiceResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ServiceResult<T> {
    return try {
        val result = apiCall()
        ServiceResult.Success(result)
    } catch (e: RedirectResponseException) {
        // Error 3xx
        ServiceResult.Error(
            code = e.response.status.value.toString(),
            message = "Erro de redirecionamento: ${e.response.status.description}"
        )
    } catch (e: ClientRequestException) {
        // Error 4xx
        ServiceResult.Error(
            code = e.response.status.value.toString(),
            message = "Erro de requisição: ${e.response.status.description}"
        )
    } catch (e: ServerResponseException) {
        // Error 5xx
        ServiceResult.Error(
            code = e.response.status.value.toString(),
            message = "Erro no servidor: ${e.response.status.description}"
        )
    } catch (e: ErrorResponseException) {
        // Backend exceptions
        ServiceResult.Error(
            code = e.error.httpCode.toString(),
            message = "Erro backend: ${e.error.message}"
        )
    } catch (e: Exception) {
        // IOException, TimeoutException, etc
        ServiceResult.Error(message = "Erro inesperado: ${e.message}")
    }
}