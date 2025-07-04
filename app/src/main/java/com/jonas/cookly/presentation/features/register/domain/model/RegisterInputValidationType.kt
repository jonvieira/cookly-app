package com.jonas.cookly.presentation.features.register.domain.model

sealed class RegisterInputValidationType {
    data object EmptyField : RegisterInputValidationType()
    data object NoEmail : RegisterInputValidationType()
    data object PasswordsDoNotMatch : RegisterInputValidationType()
    data object PasswordTooShort : RegisterInputValidationType()
    data object PhoneNumberInvalid : RegisterInputValidationType()
    data object PasswordNumberMissing : RegisterInputValidationType()
    data object PasswordUpperCaseMissing : RegisterInputValidationType()
    data object PasswordSpecialCharMissing : RegisterInputValidationType()
    data object Valid : RegisterInputValidationType()

    val errorMessage: String?
        get() = when (this) {
            EmptyField -> "Preencha todos os campos"
            NoEmail -> "Email inválido"
            PasswordsDoNotMatch -> "As senhas não coincidem"
            PasswordTooShort -> "Senha muito curta"
            PhoneNumberInvalid -> "O número de telefone precisa conter 11 caracteres"
            PasswordNumberMissing -> "A senha precisa conter pelo menos um número"
            PasswordUpperCaseMissing -> "A senha precisa conter pelo menos um caractere maiúsculo"
            PasswordSpecialCharMissing -> "A senha precisa conter pelo menos um caractere especial"
            Valid -> null
        }

    val isValid: Boolean
        get() = this == Valid
}