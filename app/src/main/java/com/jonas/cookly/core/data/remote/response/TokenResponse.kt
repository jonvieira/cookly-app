package com.jonas.cookly.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jonas.cookly.ui.features.login.domain.model.TokenResponseModel

data class TokenResponse(
    @SerializedName("isSuccessful")
    val isSuccessful: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("userName")
    val userName: String
)

fun TokenResponse.toTokenResponseModel() = TokenResponseModel(
    isSuccess = isSuccessful,
    message = message,
    token = token,
    userName = userName
)
