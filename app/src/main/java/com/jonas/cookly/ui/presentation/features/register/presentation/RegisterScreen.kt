package com.jonas.cookly.ui.presentation.features.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.jonas.cookly.ui.presentation.features.register.presentation.components.RegisterContent
import com.jonas.cookly.ui.presentation.features.register.presentation.state.RegisterUserState
import com.jonas.cookly.ui.presentation.navigation.NavHelper

@Composable
fun RegisterScreen(
    uiState: RegisterUserState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onTogglePasswordIconClick: () -> Unit,
    onTogglePasswordRepeatedIconClick: () -> Unit,
    onRegisterButtonClick: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    NavHelper(
        shouldNavigate = {
            uiState.isSuccessfullyRegistered
        },
        destination = {
            onNavigateToLogin()
        }
    )

    Scaffold(
        content = { paddingValues ->
            RegisterContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordRepeatedChanged = onPasswordRepeatedChanged,
                onTogglePasswordIconClick = onTogglePasswordIconClick,
                onTogglePasswordRepeatedIconClick = onTogglePasswordRepeatedIconClick,
                onRegisterButtonClick = onRegisterButtonClick,
                onNavigateToLogin = onNavigateToLogin,
            )
        }
    )
}