package com.jonas.cookly.ui.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterScreen
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserViewModel
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Email
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Name
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Password
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.PasswordConfirmation
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Phone
import com.jonas.cookly.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.registerScreen(
    onNavigateToLogin: () -> Unit
) {
    composable<AuthScreens.RegisterScreen> {
        val viewModel = hiltViewModel<RegisterUserViewModel>()
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel.collectAsStateWithLifecycle(null)

        RegisterScreen(
            uiState = uiState.value,
            onNameChanged = { viewModel.onInputChange(Name, it) },
            onEmailChanged = { viewModel.onInputChange(Email, it) },
            onPhoneChanged = { viewModel.onInputChange(Phone, it) },
            onPasswordChanged = { viewModel.onInputChange(Password, it) },
            onPasswordRepeatedChanged = { viewModel.onInputChange(PasswordConfirmation, it) },
            onTogglePasswordIconClick = { viewModel.onTogglePasswordVisibility() },
            onTogglePasswordRepeatedIconClick = { viewModel.onTogglePasswordConfirmationVisibility() },
            onRegisterButtonClick = {},
            onNavigateToLogin = onNavigateToLogin
        )
    }
}
