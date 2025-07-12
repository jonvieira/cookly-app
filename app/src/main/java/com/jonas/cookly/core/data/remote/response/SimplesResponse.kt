package com.jonas.cookly.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jonas.cookly.core.domain.SimplesResponseModel

data class SimplesResponse(
    @SerializedName("isSuccessful")
    val isSuccessful: Boolean,
    @SerializedName("message")
    val message: String
)

fun SimplesResponse.toSimplesResponseModel() = SimplesResponseModel(
    isSuccessful = isSuccessful,
    message = message
)
