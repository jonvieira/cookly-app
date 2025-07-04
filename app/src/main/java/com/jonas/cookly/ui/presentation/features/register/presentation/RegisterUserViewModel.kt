package com.jonas.cookly.ui.presentation.features.register.presentation

import androidx.lifecycle.ViewModel
import com.jonas.cookly.core.sideeffects.SideEffect
import com.jonas.cookly.ui.presentation.features.register.domain.model.RegisterInputValidationType
import com.jonas.cookly.ui.presentation.features.register.domain.usecase.RegisterUserUseCase
import com.jonas.cookly.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.jonas.cookly.ui.presentation.features.register.presentation.state.RegisterUserState
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUserState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onTogglePasswordConfirmationVisibility() {
        _uiState.update { it.copy(isPasswordConfirmationVisible = !it.isPasswordConfirmationVisible) }
    }

    fun onInputChange(field: RegisterField, newValue: String) {
        _uiState.update {
            when (field) {
                RegisterField.Name -> it.copy(nameInput = newValue)
                RegisterField.Email -> it.copy(emailInput = newValue)
                RegisterField.Phone -> it.copy(phoneInput = newValue)
                RegisterField.Password -> it.copy(passwordInput = newValue)
                RegisterField.PasswordConfirmation -> it.copy(passwordConfirmationInput = newValue)
            }
        }

        validateInput()
    }

    private fun validateInput() {
        val validationResult = validateRegisterInputUseCase(
            name = _uiState.value.nameInput,
            email = _uiState.value.emailInput,
            phone = _uiState.value.phoneInput,
            password = _uiState.value.passwordInput,
            passwordConfirmation = _uiState.value.passwordConfirmationInput
        )
        processValidationResult(validationResult)
    }

    private fun processValidationResult(validationResult: RegisterInputValidationType) {
        _uiState.update {
            it.copy(
                errorMessageInput = validationResult.errorMessage,
                isInputValid = validationResult.isValid
            )
        }
    }
}