package com.jonas.cookly.ui.features.login.domain.usecase

import com.jonas.cookly.ui.features.login.domain.model.LoginInputValidationType

interface ValidateLoginInputUseCase {
    operator fun invoke(
        email: String,
        password: String
    ): LoginInputValidationType
}

@Suppress("ReturnCount")
class ValidateLoginInputUseCaseImpl : ValidateLoginInputUseCase {
    override fun invoke(
        email: String,
        password: String
    ): LoginInputValidationType {
        if (listOf(email, password).any { it.isBlank() }) {
            return LoginInputValidationType.EmptyField
        }

        if (!email.contains("@")) {
            return LoginInputValidationType.NoEmail
        }

        return LoginInputValidationType.Valid
    }
}
