package com.jonas.cookly.ui.presentation.features.register.domain.model

import com.jonas.cookly.core.data.remote.requests.AddUserRequest

data class AddUserRequestModel(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)

fun AddUserRequestModel.toAddUserRequest() = AddUserRequest(
    name = name,
    email = email,
    password = password,
    phone = phone
)
