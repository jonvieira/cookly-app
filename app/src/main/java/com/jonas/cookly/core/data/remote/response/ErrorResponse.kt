package com.jonas.cookly.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("httpCode")
    val httpCode: Int,
    @SerializedName("message")
    val message: String,
)
