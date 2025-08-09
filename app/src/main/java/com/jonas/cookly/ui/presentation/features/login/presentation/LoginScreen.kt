package com.jonas.cookly.ui.presentation.features.login.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonas.cookly.core.util.extensions.showToast
import com.jonas.cookly.core.util.sideeffect.SideEffect
import com.jonas.cookly.core.util.sideeffect.SingleEventEffect
import com.jonas.cookly.ui.presentation.features.login.presentation.components.LoginContent
import com.jonas.cookly.ui.presentation.navigation.NavHelper

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val viewModel = hiltViewModel<LoginViewModel>()
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
            uiState.value.isSuccessfulLogin
        },
        destination = {
            onNavigateToHome()
        }
    )

    Scaffold(
        content = { paddingValues ->
            LoginContent(
                paddingValues = paddingValues,
                uiState = uiState.value,
                onEmailValueChange = { viewModel.onEvent(LoginEvent.OnEmailValueChange(it)) },
                onPasswordValueChange = { viewModel.onEvent(LoginEvent.OnPasswordValueChange(it)) },
                onTogglePasswordIconClick = { viewModel.onEvent(LoginEvent.OnTogglePasswordIconClick) },
                onLoginClick = { viewModel.onEvent(LoginEvent.OnLoginClick) },
                onNavigateToRegister = onNavigateToRegister
            )
        }
    )
}
