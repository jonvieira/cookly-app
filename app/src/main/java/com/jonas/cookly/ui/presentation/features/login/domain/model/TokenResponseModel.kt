package com.jonas.cookly.ui.presentation.features.login.domain.model

data class TokenResponseModel(
    val isSuccess: Boolean,
    val message: String? = null,
    val token: String? = null,
    val userName: String? = null
)