package com.jonas.cookly.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.jonas.cookly.ui.presentation.navigation.loginScreen
import com.jonas.cookly.ui.presentation.navigation.registerScreen
import com.jonas.cookly.ui.presentation.navigation.screens.AuthScreens
import com.jonas.cookly.ui.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ) {
        loginScreen(
            onNavigateToHome = onNavigateToHome,
            onNavigateToRegister = onNavigateToRegister
        )

        registerScreen(
            onNavigateToLogin = {}
        )
    }
}