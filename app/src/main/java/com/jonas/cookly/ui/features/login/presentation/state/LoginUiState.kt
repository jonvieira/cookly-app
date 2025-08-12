package com.jonas.cookly.ui.features.login.presentation.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val errorMessageInput: String? = null,
    val isSuccessfulLogin: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
