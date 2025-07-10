package com.jonas.cookly.core.data.remote.response

import com.google.gson.annotations.SerializedName

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
