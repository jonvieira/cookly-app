package com.jonas.cookly.ui.presentation.features.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonas.cookly.core.sideeffects.SideEffect
import com.jonas.cookly.core.util.SingleEventEffect
import com.jonas.cookly.core.util.extensions.showToast
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnEmailChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnNameChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnPasswordChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnPasswordConfirmationChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnPhoneChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnRegisterClick
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnTogglePasswordConfirmationVisibility
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnTogglePasswordVisibility
import com.jonas.cookly.ui.presentation.features.register.presentation.components.RegisterContent
import com.jonas.cookly.ui.presentation.navigation.NavHelper

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    val viewModel = hiltViewModel<RegisterUserViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffectFlow = viewModel.sideEffectChannel

    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow) { sideEffect ->
        when (sideEffect) {
            is SideEffect.ShowToast -> {
                context.showToast(sideEffect.message)
            }
        }
    }

    NavHelper(
        shouldNavigate = {
            uiState.value.isSuccessfullyRegistered
        },
        destination = {
            onNavigateToLogin()
        }
    )

    Scaffold(
        content = { paddingValues ->
            RegisterContent(
                paddingValues = paddingValues,
                uiState = uiState.value,
                onNameChanged = { viewModel.onEvent(OnNameChanged(it)) },
                onEmailChanged = { viewModel.onEvent(OnEmailChanged(it)) },
                onPhoneChanged = { viewModel.onEvent(OnPhoneChanged(it)) },
                onPasswordChanged = { viewModel.onEvent(OnPasswordChanged(it)) },
                onPasswordRepeatedChanged = { viewModel.onEvent(OnPasswordConfirmationChanged(it)) },
                onTogglePasswordIconClick = { viewModel.onEvent(OnTogglePasswordVisibility) },
                onTogglePasswordRepeatedIconClick = {
                    viewModel.onEvent(
                        OnTogglePasswordConfirmationVisibility
                    )
                },
                onRegisterButtonClick = { viewModel.onEvent(OnRegisterClick) },
                onNavigateToLogin = onNavigateToLogin
            )
        }
    )
}