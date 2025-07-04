package com.jonas.cookly.presentation.features.register.presentation.state

data class RegisterUserState(
    val nameInput: String = "",
    val emailInput: String = "",
    val phoneInput: String = "",
    val passwordInput: String = "",
    val passwordConfirmationInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isPasswordConfirmationVisible: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegistered: Boolean = false,
    val errorMessage: String? = null
)
