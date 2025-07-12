package com.jonas.cookly.ui.presentation.features.register.domain.usecase

import com.jonas.cookly.core.util.extensions.containsNumber
import com.jonas.cookly.core.util.extensions.containsSpecialChar
import com.jonas.cookly.core.util.extensions.containsUpperCase
import com.jonas.cookly.ui.presentation.features.register.domain.model.RegisterInputValidationType

interface ValidateRegisterInputUseCase {
    operator fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String,
        passwordConfirmation: String
    ): RegisterInputValidationType
}

@Suppress("ReturnCount")
class ValidateRegisterInputUseCaseImpl : ValidateRegisterInputUseCase {
    override fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String,
        passwordConfirmation: String
    ): RegisterInputValidationType {
        if (listOf(name, email, phone, password, passwordConfirmation).any { it.isBlank() }) {
            return RegisterInputValidationType.EmptyField
        }

        if (!email.contains("@")) {
            return RegisterInputValidationType.NoEmail
        }

        if (password != passwordConfirmation) {
            return RegisterInputValidationType.PasswordsDoNotMatch
        }

        if (password.length < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }

        if (phone.length < 11) {
            return RegisterInputValidationType.PhoneNumberInvalid
        }

        if (!password.containsNumber()) {
            return RegisterInputValidationType.PasswordNumberMissing
        }

        if (!password.containsUpperCase()) {
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }

        if (!password.containsSpecialChar()) {
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }

        return RegisterInputValidationType.Valid
    }
}
