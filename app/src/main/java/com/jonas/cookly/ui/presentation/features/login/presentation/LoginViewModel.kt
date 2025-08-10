package com.jonas.cookly.ui.presentation.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonas.cookly.core.util.extensions.observeState
import com.jonas.cookly.core.util.sideeffect.SideEffect
import com.jonas.cookly.ui.presentation.features.login.domain.model.AuthUserRequestModel
import com.jonas.cookly.ui.presentation.features.login.domain.model.LoginInputValidationType
import com.jonas.cookly.ui.presentation.features.login.domain.usecase.LoginUseCase
import com.jonas.cookly.ui.presentation.features.login.domain.usecase.ValidateLoginInputUseCase
import com.jonas.cookly.ui.presentation.features.login.presentation.state.LoginUiState
import com.jonas.cookly.ui.presentation.features.login.presentation.util.LoginField
import com.jonas.cookly.ui.presentation.features.login.presentation.util.LoginField.Email
import com.jonas.cookly.ui.presentation.features.login.presentation.util.LoginField.Password
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailValueChange -> onInputChange(Email, event.email)
            is LoginEvent.OnPasswordValueChange -> onInputChange(Password, event.password)
            is LoginEvent.OnTogglePasswordIconClick -> onTogglePasswordVisibility()
            is LoginEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    private fun onInputChange(field: LoginField, newValue: String) {
        _uiState.update {
            when (field) {
                Email -> it.copy(email = newValue)
                Password -> it.copy(password = newValue)
            }
        }

        validateInput()
    }

    private fun validateInput() {
        val validationResult = validateLoginInputUseCase(
            email = _uiState.value.email,
            password = _uiState.value.password
        )
        processValidationResult(validationResult)
    }

    private fun processValidationResult(validationResult: LoginInputValidationType) {
        _uiState.update {
            it.copy(
                errorMessageInput = validationResult.errorMessage,
                isInputValid = validationResult.isValid
            )
        }
    }

    private fun onLoginClick() {
        viewModelScope.launch {
            loginUseCase.invoke(
                params = LoginUseCase.Params(
                    AuthUserRequestModel(
                        email = _uiState.value.email,
                        password = _uiState.value.password
                    )
                )
            ).observeState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onError = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageLoginProcess = error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccessfulLogin = response.isSuccess
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message.toString()))
                }
            )
        }
    }
}