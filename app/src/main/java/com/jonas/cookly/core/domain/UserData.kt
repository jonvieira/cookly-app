package com.jonas.cookly.core.domain

data class UserData(
    val token: String? = "",
    val userName: String? = "",
    val errorMessage: String? = null
)
