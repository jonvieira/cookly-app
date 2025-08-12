package com.jonas.cookly.ui.features.login.domain.model

sealed class LoginInputValidationType {
    data object EmptyField : LoginInputValidationType()
    data object NoEmail : LoginInputValidationType()
    data object Valid : LoginInputValidationType()

    val errorMessage: String?
        get() = when (this) {
            EmptyField -> "Preencha todos os campos"
            NoEmail -> "Email inválido"
            Valid -> null
        }

    val isValid: Boolean
        get() = this == Valid
}
