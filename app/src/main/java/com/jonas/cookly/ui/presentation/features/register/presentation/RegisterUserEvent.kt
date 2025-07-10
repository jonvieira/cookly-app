package com.jonas.cookly.ui.presentation.features.register.presentation

sealed class RegisterUserEvent {
    data class OnNameChanged(val name: String) : RegisterUserEvent()
    data class OnEmailChanged(val email: String) : RegisterUserEvent()
    data class OnPhoneChanged(val phone: String) : RegisterUserEvent()
    data class OnPasswordChanged(val password: String) : RegisterUserEvent()
    data class OnPasswordConfirmationChanged(val passwordConfirmation: String) : RegisterUserEvent()
    data object OnRegisterClick : RegisterUserEvent()
    data object OnTogglePasswordVisibility : RegisterUserEvent()
    data object OnTogglePasswordConfirmationVisibility : RegisterUserEvent()
}