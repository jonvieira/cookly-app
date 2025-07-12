package com.jonas.cookly.ui.presentation.features.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonas.cookly.core.util.extensions.observeState
import com.jonas.cookly.core.util.extensions.toFormattedPhoneNumber
import com.jonas.cookly.core.util.sideeffect.SideEffect
import com.jonas.cookly.ui.presentation.features.register.domain.model.AddUserRequestModel
import com.jonas.cookly.ui.presentation.features.register.domain.model.RegisterInputValidationType
import com.jonas.cookly.ui.presentation.features.register.domain.usecase.RegisterUserUseCase
import com.jonas.cookly.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnEmailChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnNameChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnPasswordChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnPasswordConfirmationChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnPhoneChanged
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnRegisterClick
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnTogglePasswordConfirmationVisibility
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterUserEvent.OnTogglePasswordVisibility
import com.jonas.cookly.ui.presentation.features.register.presentation.state.RegisterUserState
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Email
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Name
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Password
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.PasswordConfirmation
import com.jonas.cookly.ui.presentation.features.register.presentation.util.RegisterField.Phone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun onEvent(event: RegisterUserEvent) {
        when (event) {
            is OnNameChanged -> onInputChange(Name, event.name)
            is OnEmailChanged -> onInputChange(Email, event.email)
            is OnPhoneChanged -> onInputChange(Phone, event.phone)
            is OnPasswordChanged -> onInputChange(Password, event.password)
            is OnPasswordConfirmationChanged -> onInputChange(
                PasswordConfirmation,
                event.passwordConfirmation
            )
            is OnTogglePasswordVisibility -> onTogglePasswordVisibility()
            is OnTogglePasswordConfirmationVisibility -> onTogglePasswordConfirmationVisibility()
            is OnRegisterClick -> onRegisterClick()
        }
    }

    private fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    private fun onTogglePasswordConfirmationVisibility() {
        _uiState.update { it.copy(isPasswordConfirmationVisible = !it.isPasswordConfirmationVisible) }
    }

    private fun onInputChange(field: RegisterField, newValue: String) {
        _uiState.update {
            when (field) {
                Name -> it.copy(nameInput = newValue)
                Email -> it.copy(emailInput = newValue)
                Phone -> it.copy(phoneInput = newValue)
                Password -> it.copy(passwordInput = newValue)
                PasswordConfirmation -> it.copy(passwordConfirmationInput = newValue)
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

    private fun onRegisterClick() {
        viewModelScope.launch {
            registerUserUseCase.invoke(
                params = RegisterUserUseCase.Params(buildUserRequestModel())
            ).observeState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onError = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccessfullyRegistered = response.isSuccessful
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                }
            )
        }
    }

    private fun buildUserRequestModel() = AddUserRequestModel(
        name = _uiState.value.nameInput,
        email = _uiState.value.emailInput,
        phone = _uiState.value.phoneInput.toFormattedPhoneNumber(),
        password = _uiState.value.passwordInput
    )
}
