package com.jonas.cookly.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jonas.cookly.ui.presentation.features.login.LoginScreen
import com.jonas.cookly.ui.presentation.features.register.presentation.RegisterScreen
import com.jonas.cookly.ui.presentation.navigation.screens.AuthScreens
import com.jonas.cookly.ui.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ) {
        composable<AuthScreens.RegisterScreen> {
            RegisterScreen(
                onNavigateToLogin = onNavigateToLogin
            )
        }

        composable<AuthScreens.LoginScreen> {
            LoginScreen(
                onNavigateToRegister = onNavigateToRegister
            )
        }
    }
}
