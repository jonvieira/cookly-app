package com.jonas.cookly.ui.features.login.domain.model

import com.jonas.cookly.core.data.remote.requests.AuthUserRequest

data class AuthUserRequestModel(
    val email: String,
    val password: String
)

fun AuthUserRequestModel.toAddUserRequest() = AuthUserRequest(
    email = email,
    password = password
)