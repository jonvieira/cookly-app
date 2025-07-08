package com.jonas.cookly.ui.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonas.cookly.ui.presentation.features.login.LoginScreen
import com.jonas.cookly.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<AuthScreens.LoginScreen> {
        LoginScreen(
            onNavigateToRegister = onNavigateToRegister
        )
    }
}