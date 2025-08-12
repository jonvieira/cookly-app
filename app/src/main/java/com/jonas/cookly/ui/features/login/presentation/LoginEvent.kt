package com.jonas.cookly.ui.features.login.presentation

sealed class LoginEvent {
    data class OnEmailValueChange(val email: String) : LoginEvent()
    data class OnPasswordValueChange(val password: String) : LoginEvent()
    data object OnTogglePasswordIconClick : LoginEvent()
    data object OnLoginClick : LoginEvent()
}
